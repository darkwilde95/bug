/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bug;

/**
 *
 * @author darkw
 */
public class Errors {
  
  public static void notDeclared(String var) {
    System.err.println("Variable '" + var + "' was not declared");
    System.exit(-1);
  }
  
  public static void buildError() {
    System.err.println("Error!");
    System.exit(-1);
  }
  
  public static void incompatibleTypes(int leftType, int rightType, String op) {
    String left = leftType == Type.INT ? "integer" : "boolean";
    String right = rightType == Type.INT ? "integer" : "boolean";
    System.err.println("Incompatible types: '" + left + "' " + op + " '" + right + "'");
    System.exit(-1);
  }
  
  public static void areNot(int type) {
    String t = type == Type.INT ? "integer" : "boolean";
    System.err.println("Variables are not '" + t + "' type");
    System.exit(-1);
  }
  
  public static void isNot(int type) {
    String t = type == Type.INT ? "integer" : "boolean";
    System.err.println("Type is not '" + t + "'");
    System.exit(-1);
  }
  
  public static void operationNotSupported(int leftType, int rightType, String op) {
    String left = leftType == Type.INT ? "integer" : "boolean";
    String right = rightType == Type.INT ? "integer" : "boolean";
    System.err.println("Operation not supported: '" + left + "' " + op + " '" + right + "'");
    System.exit(-1);
  }
  
}
