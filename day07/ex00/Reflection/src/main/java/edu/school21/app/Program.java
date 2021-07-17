package edu.school21.app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

public class Program {
    private static final String CLASS_PATH = "edu.school21.classes.";

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Classes:\n" +
                    "User\n" +
                    "Car\n" +
                    "------------------\n" +
                    "Enter class name:");

            String className = scanner.nextLine();
            System.out.println("------------------");

            Object object = Class.forName(CLASS_PATH + className).getConstructor().newInstance();

            List<Field> fields = new ArrayList<>(Arrays.asList(object.getClass().getDeclaredFields()));
            List<Method> methods = new ArrayList<>(Arrays.asList(object.getClass().getDeclaredMethods()));

            System.out.println("fields: ");
            fields.forEach(field -> {
                System.out.printf("\t%s %s\n", field.getType().getSimpleName(), field.getName());
            });

            System.out.println("methods: ");

            methods.forEach(method -> {
                StringBuilder stringTypes = new StringBuilder();

                Type[] types = method.getParameterTypes();

                for (int i = 0; i < types.length; i++) {
                    String typeName = getTypeSimpleName(types[i].getTypeName());

                    stringTypes.append(typeName);

                    if (i < types.length - 1) {
                        stringTypes.append(", ");
                    }
                }

                System.out.printf("\t%s %s(%s)\n", method.getReturnType().getSimpleName(), method.getName(), stringTypes);
            });

            System.out.println("------------------");
            System.out.println("Let's create an object.");

            for (Field field : fields) {
                System.out.println(field.getName() + ":");

                String fieldValue = scanner.nextLine();

                setFieldValue(field, fieldValue, object);
            }

            System.out.println("Object created: " + object.toString());

            System.out.println("------------------");

            System.out.println("Enter name of the field for changing:");

            String nameFieldForChange = scanner.nextLine();

            Field fieldForChange = getFieldByName(fields, nameFieldForChange);

            System.out.println("Enter " + fieldForChange.getType() + " value");

            String newFieldValue = scanner.nextLine();

            setFieldValue(fieldForChange, newFieldValue, object);

            System.out.println("Object updated: " + object.toString());

            System.out.println("------------------");

            System.out.println("Enter name of the method for call:");

            String methodName = scanner.nextLine();

            methodName = methodName.substring(0, methodName.indexOf("("));

            Method method = getMethodByName(methods, methodName);

            Type[] parameterTypes = method.getGenericParameterTypes();

            Object[] parameterValues = new Object[parameterTypes.length];

            int i = 0;
            for (Type type : parameterTypes) {
                System.out.println("Enter " + getTypeSimpleName(type.getTypeName()) + " value");

                String parameterValue = scanner.nextLine();

                if (type.equals(Integer.class) || type.equals(int.class)) {
                    parameterValues[i++] = Integer.parseInt(parameterValue);
                } else if (type.equals(Long.class) || type.equals(long.class)) {
                    parameterValues[i++] = Long.parseLong(parameterValue);
                } else if (type.equals(Double.class) || type.equals(double.class)) {
                    parameterValues[i++] = Double.parseDouble(parameterValue);
                } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
                    parameterValues[i++] = Boolean.parseBoolean(parameterValue);
                } else {
                    parameterValues[i++] = parameterValue;
                }
            }

            method.setAccessible(true);

            Object result = method.invoke(object, parameterValues);

            if (result != null) {
                System.out.println("Method returned:");
                System.out.println(result);
            }

            scanner.close();
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Method getMethodByName(List<Method> methods, String methodName) {
        Method method = null;

        for (int i = 0; i < methods.size(); i++) {
            if (methods.get(i).getName().equals(methodName)) {
                method = methods.get(i);
            }
        }

        return method;
    }

    private static void setFieldValue(Field field, String fieldValue, Object object) {
        try {
            field.setAccessible(true);

            if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                field.setInt(object, Integer.parseInt(fieldValue));
            } else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
                field.setLong(object, Long.parseLong(fieldValue));
            } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
                field.setDouble(object, Double.parseDouble(fieldValue));
            } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
                field.setBoolean(object, Boolean.parseBoolean(fieldValue));
            } else {
                field.set(object, fieldValue);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Field getFieldByName(List<Field> fields, String nameFieldForChange) {
        Field field = null;

        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getName().equals(nameFieldForChange)) {
                field = fields.get(i);
            }
        }

        return field;
    }

    private static String getTypeSimpleName(String typeName) {
        String simpleName = typeName;

        if (typeName.lastIndexOf(".") > 0) {
            simpleName = typeName.substring(typeName.lastIndexOf(".") + 1);
        }

        return simpleName;
    }
}
