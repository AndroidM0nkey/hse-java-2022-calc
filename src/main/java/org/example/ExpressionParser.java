package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.*;

import org.operator.Operator;
import org.reflections.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;


public class ExpressionParser {
    //todo template for codegen

    private HashMap<String, Operator> createOperationsMap() throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        HashMap<String, Operator> operationsMap = new HashMap<String, Operator>();

        //reflexions for main lib
        Reflections reflections = new Reflections("org.example");
        Set<Class<? extends Operator>> subTypes =
                reflections.getSubTypesOf(Operator.class);


        //reflexions for user lib
        Reflections reflectionsU = new Reflections(new ConfigurationBuilder().forPackages("org.mathlib"));
        Set<Class<? extends Operator>> subTypesU =
                reflectionsU.getSubTypesOf(Operator.class);


        Iterator<Class<? extends Operator>> itr = subTypes.iterator();

        while(itr.hasNext()){
            Class<? extends Operator> cl = itr.next();
            Class<?> c = Class.forName(cl.getName());
            Constructor<?> cons = c.getConstructor();
            Object object = cons.newInstance();
            Operator op = (Operator)object;
            operationsMap.put(op.name, op);
        }

        Iterator<Class<? extends Operator>> itrU = subTypesU.iterator();

        while(itrU.hasNext()) {
            Class<? extends Operator> cl = itrU.next();
            Class<?> c = Class.forName(cl.getName());
            Constructor<?> cons = c.getConstructor();
            Object object = cons.newInstance();
            Operator op = (Operator) object;
            operationsMap.put(op.name, op);
        }

        return operationsMap;
    }

    public Expression parseInput(String inputString) {
        String[] args = inputString.split("\\s+");
        boolean unparsed = false;

        String modString = args[0];
        BigInteger mod = BigInteger.valueOf(0);
        try {
            mod = new BigInteger(modString);
        } catch (Exception e) {
            unparsed = true;
        }

        if (unparsed) {
            System.out.println("Wrong first 2 characters format");
            return new Expression(unparsed); //todo make custom exception
        }

        if (args.length % 2 != 1 || args.length <= 2 || !args[1].equals("|")) {
            System.out.println("Wrong first 2 characters format");
            unparsed = true;
        }

        BigInteger[] valuesList = new BigInteger[(args.length - 1)/2];
        try {
            for (int i = 2; i < args.length; i+=2) {
                    valuesList[(i - 2)/2] = new BigInteger(args[i]);
            }
        } catch (Exception e) {
            unparsed = true;
        }

        if (unparsed) {
            System.out.println("Cant parse numbers");
            return new Expression(unparsed); //todo make custom exception
        }

        HashMap<String, Operator> operationsMap = null;
        try {
            operationsMap = createOperationsMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<Object> operators = new ArrayList<Object>();

        try {
            for (int i = 3; i < args.length; i += 2) {
                if (operationsMap.get(args[i]) != null) {
                    operators.add(operationsMap.get(args[i]));
                } else {
                    unparsed = true;
                    break;
                }
            }
        } catch (Exception e) {
            unparsed = true;
        }

        if (unparsed) {
            System.out.println("Cant parse operators");
            return new Expression(unparsed); //todo make custom exception
        }

        return new Expression(mod, operators, valuesList, unparsed);
    }
}


