package de.lezleoh.mathgame.view.util.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AccessPrivate {
	public static Object getPrivateField(Object objectContainingField,
			String fieldName) {
		Class<?> classUnderTest = objectContainingField.getClass();
		try {
			Field field = classUnderTest.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(objectContainingField);
		} catch (NoSuchFieldException x) {
			System.err
					.printf("reflection error: Field '%s' doesn't exist in fieldlist of '%s' '%n'",
							fieldName, objectContainingField.getClass()
									.getName());
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			System.err
					.printf("reflection error: Tried to access field '%s' of class '%s', but field was not accessible '%n'",
							fieldName, objectContainingField.getClass()
									.getName());
			x.printStackTrace();
		}
		return null;
	}
	
	public static Object genericInvokMethod(Object obj, String methodName,
            int paramCount, Object... params) {
        Method method;
        Object requiredObj = null;
        Object[] parameters = new Object[paramCount];
        Class<?>[] classArray = new Class<?>[paramCount];
        for (int i = 0; i < paramCount; i++) {
            parameters[i] = params[i];
            classArray[i] = params[i].getClass();
        }
        try {
            method = obj.getClass().getDeclaredMethod(methodName, classArray);
            method.setAccessible(true);
            requiredObj = method.invoke(obj, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return requiredObj;
    }
}
