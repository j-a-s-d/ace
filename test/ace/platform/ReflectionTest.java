/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class ReflectionTest {
	
	@Test public void testGetConstructorAsAccessible() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final Constructor c = Reflection.getConstructorAsAccessible(Runtime.class);
		Assert.assertNotNull(c);
		Assert.assertNotNull((Runtime) c.newInstance());
	}
	
	class Foo {
		private String bar() {
			return "testing";
		}
	}
	
	@Test public void testGetMethodAsAccessible() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Assert.assertNull(Reflection.getMethodAsAccessible(Foo.class, "blah"));
		final Method barMethod = Reflection.getMethodAsAccessible(Foo.class, "bar");
		Assert.assertNotNull(barMethod);
		final Foo foo = new Foo();
		final Object result = barMethod.invoke(foo);
		Assert.assertEquals("testing", (String) result);
	}

	@Test public void testGetFieldAsAccessible() throws IllegalArgumentException, IllegalAccessException {
		Assert.assertNull(Reflection.getFieldAsAccessible(ArrayList.class, "blah"));
		final Field dataField = Reflection.getFieldAsAccessible(ArrayList.class, "elementData");
		Assert.assertNotNull(dataField);
		final ArrayList list = new ArrayList();
		final Object[] data = (Object[]) dataField.get(list);
		Assert.assertNotNull(data);
		Assert.assertTrue(data.length > -1);
	}

}