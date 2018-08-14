package rpc.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkHandler implements InvocationHandler {

    final Object delegate;

    JdkHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects)
            throws Throwable {
        //添加代理逻辑<1>
        if(method.getName().equals("sell")){
            System.out.print("");
        }
        return null;
//        return method.invoke(delegate, objects);
    }
    
    public static BookApi createJdkDynamicProxy(final BookApi delegate) {
        BookApi jdkProxy = (BookApi) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{BookApi.class}, new JdkHandler(delegate));
        return jdkProxy;
    }
}
