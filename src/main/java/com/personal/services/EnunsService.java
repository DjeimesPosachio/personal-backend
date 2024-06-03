package com.personal.services;

import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnunsService {

    public Map<String, List<Map<String, String>>> getAllEnums(String packagePath) {
        Map<String, List<Map<String, String>>> result = new HashMap<>();

        try {
            Class<?>[] classes = getClasses(packagePath);
            for (Class<?> clazz : classes) {
                if (clazz.isEnum()) {
                    List<Map<String, String>> enumsList = new ArrayList<>();
                    for (Object enumConstant : clazz.getEnumConstants()) {
                        Field field = clazz.getDeclaredField(((Enum<?>) enumConstant).name());
                        if (Modifier.isFinal(field.getModifiers())) {
                            Map<String, String> enumMap = new HashMap<>();
                            enumMap.put("key", ((Enum<?>) enumConstant).name());
                            Field descricaoField = clazz.getDeclaredField("descricao");
                            descricaoField.setAccessible(true);
                            String descricao = (String) descricaoField.get(enumConstant);
                            enumMap.put("label", descricao);
                            enumsList.add(enumMap);
                        }
                    }
                    result.put(clazz.getSimpleName(), enumsList);
                }
            }
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Class<?>[] getClasses(String packageName) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        java.net.URL resource = classLoader.getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("Pacote não encontrado: " + packageName);
        }
        java.io.File directory = new java.io.File(resource.getFile());
        if (!directory.exists()) {
            throw new IllegalArgumentException("Pacote não encontrado: " + packageName);
        }
        java.util.ArrayList<Class<?>> classes = new java.util.ArrayList<Class<?>>();
        for (java.io.File file : directory.listFiles()) {
            if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classes.add(Class.forName(className));
            }
        }
        return classes.toArray(new Class[classes.size()]);
    }
}