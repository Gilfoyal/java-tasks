package ru.mail.polis.homework.io.blocking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Вам нужно реализовать StructureOutputStream, который умеет писать данные в файл.
 * Писать поля нужно ручками, с помощью массива байт и методов {@link #write(int)}, {@link #write(byte[])} и так далее
 * 3 тугрика
 */
public class StructureOutputStream extends FileOutputStream {

    public StructureOutputStream(File name) throws FileNotFoundException {
        super(name);
    }

    /**
     * Метод должен вернуть записать прочитанную структуру.
     */
    public void write(Structure structure) throws IOException {
        writeLong(structure.getId());
        writeString(structure.getName());
        writeSubStructures(structure.getSubStructures());
        writeFloat(structure.getCoeff());
        writeBoolean(structure.isFlag1());
        writeBoolean(structure.isFlag2());
        writeBoolean(structure.isFlag3());
        writeBoolean(structure.isFlag4());
        write(structure.getParam());
    }

    /**
     * Метод должен вернуть записать массив прочитанных структур.
     */
    public void write(Structure[] structures) throws IOException {
        for (Structure structure : structures) {
            write(structure);
        }
    }

    private void writeLong(long number) throws IOException {
        byte[] data = new byte[]{
                (byte) (number >>> 56),
                (byte) (number >>> 48),
                (byte) (number >>> 40),
                (byte) (number >>> 32),
                (byte) (number >>> 24),
                (byte) (number >>> 16),
                (byte) (number >>> 8),
                (byte) (number >>> 0)
        };
        write(data);
    }

    private void writeInt(int number) throws IOException {
        byte[] data = new byte[]{
                (byte) (number >>> 24),
                (byte) (number >>> 16),
                (byte) (number >>> 8),
                (byte) (number >>> 0)
        };
        write(data);
    }

    private void writeDouble(double number) throws IOException {
        writeLong(Double.doubleToLongBits(number));
    }

    private void writeFloat(float number) throws IOException {
        writeInt(Float.floatToIntBits(number));
    }

    private void writeBoolean(boolean flag) throws IOException {
        if (flag == true) {
            writeInt(1);
        } else {
            writeInt(0);
        }
    }

    private void writeString(String string) throws IOException {
        if (string == null) {
            writeInt(-1);
            return;
        }
        byte[] data = string.getBytes();
        writeInt(data.length);
        write(data);
    }

    private void writeSubStructures(SubStructure[] subStructures) throws IOException {
        if (subStructures == null) {
            writeInt(-1);
            return;
        }
        writeInt(subStructures.length);
        for (SubStructure substructure : subStructures) {
            writeSubStructure(substructure);
        }
    }

    private void writeSubStructure(SubStructure subStructure) throws IOException {
        writeInt(subStructure.getId());
        writeString(subStructure.getName());
        writeBoolean(subStructure.isFlag());
        writeDouble(subStructure.getScore());
    }

}
