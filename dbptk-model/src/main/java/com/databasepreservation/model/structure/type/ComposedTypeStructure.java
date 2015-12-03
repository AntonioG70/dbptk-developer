/**
 *
 */
package com.databasepreservation.model.structure.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * A type composed by structuring other type. Any complex type can be
 * constructed with this type (except recursive types).
 * 
 * When created, the name of the composed type column should be passed and then
 * children types can be added using addType. When all children have been added
 * to the ComposedTypeStructure, the tree of composed/simple types can be
 * obtained with getContainedTypes. This tree is specially useful when the
 * composedType contains composedTypes
 *
 * @author Luis Faria <lfaria@keep.pt>
 * @author Bruno Ferreira <bferreira@keep.pt>
 */
// TODO: protect against recursive types
public class ComposedTypeStructure extends Type {
  private HashMap<String, ArrayList<SubType>> leafsByColumnId;
  private HashMap<String, Type> containedTypes;

  /**
   * Empty structured type constructor
   */
  public ComposedTypeStructure() {
    this.containedTypes = new HashMap<>();
  }

  /**
   * Structured type constructor
   *
   * @param typeName
   *          the name of the composed type
   */
  public ComposedTypeStructure(String typeName) {
    this.setOriginalTypeName(typeName);
    this.containedTypes = new HashMap<>();
  }

  /**
   * Adds a subtype to the composed type
   * 
   * @param name
   *          the name that can be used to refer to the subtype
   * @param type
   *          the subtype
   */
  public void addType(String name, Type type) {
    this.containedTypes.put(name, type);

    // reset leaf info to force generation of updated information
    leafsByColumnId = new HashMap<String, ArrayList<SubType>>();
  }

  /**
   * If there is a subtype that is also a composed type, its definition may be
   * lacking some subtypes. This method searches for an incomplete version of
   * betterComposedSubType and replaces it with the complete version.
   * 
   * @param betterComposedSubType
   *          a ComposedTypeStructure that contains better (more complete)
   *          definitions for its subtypes
   */
  public void completeExistingType(ComposedTypeStructure betterComposedSubType) {
    for (Map.Entry<String, Type> entry : containedTypes.entrySet()) {
      if (entry.getValue() instanceof ComposedTypeStructure) {
        ComposedTypeStructure composedSubType = (ComposedTypeStructure) entry.getValue();
        // TODO: consider UDTs defined in different schemas cross-referencing
        // each other
        if (composedSubType.getOriginalTypeName().equalsIgnoreCase(betterComposedSubType.getOriginalTypeName())) {
          containedTypes.put(entry.getKey(), betterComposedSubType);
        }
      }
    }
  }

  /**
   * Gets the subtypes that are direct children of this composed type (ie: if
   * this ComposedType is an hierarchy with more ComposedTypes, only the direct
   * descendants of the root type are returned)
   * 
   * @return an HashMap with the names and types that have been added with
   *         addType
   */
  public HashMap<String, Type> getDirectDescendantSubTypes() {
    return containedTypes;
  }

  /**
   * Gets the subtypes (children) of this composed type that are not
   * ComposedTypes (ie: if this ComposedType is an hierarchy with more
   * ComposedTypes, only the extremities of the hierarchy are returned, all
   * children composedTypes found are explored looking for more types)
   *
   * @param columnId
   *          ID of the column which type is this ComposedType (ie:
   *          schema.table.columnName)
   *
   * @return List of subtypes that are not ComposedTypes
   */
  public ArrayList<SubType> getNonComposedSubTypes(String columnId) {
    if (!leafsByColumnId.containsKey(columnId)) {
      ArrayList<SubType> leafs = new ArrayList<>();
      getLeafTypesFromComposedTypeTree(leafs, new ImmutablePair<String, ComposedTypeStructure>(columnId, this),
        new ArrayList<String>());
      leafsByColumnId.put(columnId, leafs);
    }
    return leafsByColumnId.get(columnId);
  }

  /**
   * Used to perform a depth-first search on the ComposedTypes Hierarchy (tree)
   * and build a list with information about the types on the leafs of the tree.
   * 
   * @param allTypes
   *          list with information about the types on the leafs of the tree
   *          (result accumulator)
   * @param stringAndComposedTypePair
   *          the name of the composedType and the composedType itself being
   *          processed in this iteration
   * @param currentPath
   *          list with the path from the root composedType to the leaf
   *          (auxiliary accumulator)
   */
  private void getLeafTypesFromComposedTypeTree(ArrayList<SubType> allTypes,
    Pair<String, ComposedTypeStructure> stringAndComposedTypePair, ArrayList<String> currentPath) {

    currentPath.add(stringAndComposedTypePair.getLeft());

    for (Map.Entry<String, Type> child : stringAndComposedTypePair.getValue().getDirectDescendantSubTypes().entrySet()) {
      if (child.getValue() instanceof ComposedTypeStructure) {
        ArrayList<String> namesContinued = new ArrayList<>(currentPath);
        Pair<String, ComposedTypeStructure> nextNameAndType = new ImmutablePair<>(child.getKey(),
          (ComposedTypeStructure) child.getValue());
        getLeafTypesFromComposedTypeTree(allTypes, nextNameAndType, namesContinued);
      } else {
        ArrayList<String> namesPlusOne = new ArrayList<>(currentPath);
        namesPlusOne.add(child.getKey());
        allTypes.add(new SubType(child.getKey(), namesPlusOne, child.getValue()));
      }
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((containedTypes == null) ? 0 : containedTypes.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ComposedTypeStructure other = (ComposedTypeStructure) obj;
    if (containedTypes == null) {
      if (other.containedTypes != null) {
        return false;
      }
    } // TODO: also compare containedTypes hash
    return true;
  }

  @Override
  public String toString() {
    return "ComposedTypeStructure{" + "originalTypeName=" + this.getOriginalTypeName() + "}";
  }

  /**
   * Helper class to help handling the types inside the composedTypeStructure
   */
  public static class SubType {
    private final String name;
    private final ArrayList<String> path;
    private final Type subType;

    /**
     * Disallow instance creation from outside of ComposedTypeStructure class
     */
    private SubType() {
      name = null;
      path = null;
      subType = null;
    }

    /**
     * Constructor to be used by the ComposedTypeStructure class
     */
    private SubType(String name, ArrayList<String> path, Type subType) {
      this.name = name;
      this.path = path;
      this.subType = subType;
    }

    public String getName() {
      return name;
    }

    public ArrayList<String> getPath() {
      return path;
    }

    public Type getType() {
      return subType;
    }

    @Override
    public String toString() {
      return "SubType{" + "name='" + name + '\'' + ", path=" + path + ", subType=" + subType + '}';
    }
  }
}
