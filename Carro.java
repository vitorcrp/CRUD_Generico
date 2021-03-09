import java.io.*;

public class Carro implements Registro {
	protected int id;
    protected String name;
    protected String brand;
    protected int year;
    
    public Carro() {
        id = -1;
        name = "";
        brand = "";
        year = -1;
    }
    
    public Carro(int i, String n, String b, int y) {
        id = i;
        name = n;
        brand = b;
        year = y;
    }
    
    public int getID() {
        return id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public byte[] toByteArray() throws IOException { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(name);
        dos.writeUTF(brand);
        dos.writeInt(year);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException { 
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        id = dis.readInt();
        name = dis.readUTF();
        brand = dis.readUTF();
        year = dis.readInt();
    }
      
    public String toString() {
      return "\nID: " + id + 
             "\nname: " + name + 
             "\nbrand: " + brand + 
             "\nyear: " + year;
    }

}