package top.kthirty;

import cn.hutool.core.codec.Base32;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class Test {
    public static void main(String[] args) {
        byte[] bytes = "Redis安装".getBytes();
        CRC32 c = new CRC32();
        c.reset();
        c.update(bytes);
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(c.getValue());
        byte[] array = buffer.array();
        String encode = Base32.encode(array);
        System.out.println(encode);

    }
}
