package rpc.Proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibInterceptor implements MethodInterceptor {

    final Object delegate;

    CglibInterceptor(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object intercept(Object object, Method method, Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        //添加代理逻辑
        if(method.getName().equals("sell")) {
            System.out.print("");
        }
        return null;
//        return methodProxy.invoke(delegate, objects);
    }
    
    public static BookApi createCglibDynamicProxy(final BookApi delegate) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibInterceptor(delegate));
        enhancer.setInterfaces(new Class[]{BookApi.class});
        BookApi cglibProxy = (BookApi) enhancer.create();
        return cglibProxy;
    }
}
