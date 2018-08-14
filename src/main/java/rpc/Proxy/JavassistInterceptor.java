package rpc.Proxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;

public class JavassistInterceptor {

    public static BookApi createJavassistBytecodeDynamicProxy() throws Exception {
        ClassPool mPool = new ClassPool(true);
        CtClass mCtc = mPool.makeClass(BookApi.class.getName() + "JavaassistProxy");
        mCtc.addInterface(mPool.get(BookApi.class.getName()));
        mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
        mCtc.addMethod(CtNewMethod.make(
                "public void sell() { System.out.print(\"\") ; }", mCtc));
        Class<?> pc = mCtc.toClass();
        BookApi bytecodeProxy = (BookApi) pc.newInstance();
        return bytecodeProxy;
    }
    
}
