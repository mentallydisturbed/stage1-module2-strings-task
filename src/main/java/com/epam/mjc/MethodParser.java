package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] elems = signatureString.split(" ");
        String returnType;
        String accessModifier = null;
        int pos = -1;
        for(int i = 0; i < elems.length; i++) {
            if(elems[i].contains("(")) {
                pos = i;
                break;
            }
        }

        if(pos < 2) {
            returnType = elems[0];
        }else {
            returnType = elems[1];
            accessModifier = elems[0];
        }
        String methodGeneral = elems[pos] + " ";
        for(int i = pos + 1; i < elems.length; i++) {
            methodGeneral += elems[i] + " ";
        }
        String[] parts = methodGeneral.split(("[\\(]|[\\)]"));
        String methodName = parts[0];
        String[] arguments = parts[1].split(", ");
        List<MethodSignature.Argument> argumentList = new ArrayList<>();
        for(String str : arguments) {
            if(str.length() < 1) continue;
            MethodSignature.Argument argument = new MethodSignature.Argument(str.split(" ")[0],
                    str.split(" ")[1]);
            argumentList.add(argument);
        }
        MethodSignature result = new MethodSignature(methodName, argumentList);
        result.setAccessModifier(accessModifier);
        result.setReturnType(returnType);
        return result;
    }
}
