package rpc.Serilization;

import java.io.IOException;
import java.util.LinkedList;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class ProtostuffSerialization implements Serialization {

    @Override
    public byte[] serialize(Object obj) throws IOException {
        Class clz = obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema schema = RuntimeSchema.createFrom(clz);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw e;
        } finally {
            buffer.clear();
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException {
        T message = objenesis.newInstance(clz); // <1>
        Schema<T> schema = RuntimeSchema.createFrom(clz);
        ProtostuffIOUtil.mergeFrom(bytes, message, schema);
        return message;
    }

    private Objenesis objenesis = new ObjenesisStd(); // <2>


}
