import java.io.*;
import java.lang.reflect.Constructor;


public class CRUD<T extends Registro> {

	RandomAccessFile acess; 
	Constructor<T> constructor;
	String nomeArquivo;
  

	public CRUD(Constructor<T> c, String file) throws IOException {
		this.constructor = c;
		this.acess = new RandomAccessFile(file, "rw");
	}

	public int create(T obj) throws Exception { //Método que cria novos registros
		int lastID = 0; 
		acess.seek(0); //posiciona o ponteiro no inicio do arquivo
		if (acess.length() == 0) { 
			acess.writeInt(1); //escreve o cabeçalho como 1
			obj.setID(1); 
		} else {
			lastID = acess.readInt(); 
			obj.setID(lastID + 1); 
			acess.seek(0); 
			acess.writeInt(obj.getID()); //escreve o cabeçalho como o respectivo id
		}
		byte[] mByte = obj.toByteArray();  //cria um vetor de bytes para o respectivo registro e escreve seus atributos
		long pos = acess.length(); 
		acess.seek(pos);
		acess.writeBoolean(true); 
		acess.writeShort(mByte.length);  
    	acess.write(mByte);
    	return obj.getID(); 
	}

	public T read(int id) throws Exception { //Método que recupera os registros existentes
		T obj = null;
		boolean flag = false;
		boolean lap; 
		short size;
		long lapPosition;
		int objID; 
		if(id < 0) System.out.println("Registro Inválido"); 
		else {
			acess.seek(4); //posiciona o ponteiro no primeiro registro após o cabeçalho
			while(!flag){ //lê o registro, verifica se a lapide é verdadeira e o registro tem o mesmo id passado para o método
				lap = acess.readBoolean(); 
				size = acess.readShort();
				lapPosition = acess.getFilePointer(); 
				objID = acess.readInt();
				acess.seek(lapPosition + size); //posiciona o ponteiro nas lapides dos próximos registros 
				if(acess.getFilePointer() >= acess.length()) flag = true;
				if(lap &&  id == objID){
					flag = true; 
					obj = constructor.newInstance(); //cria uma instancia de objeto utilizando o construtor
					byte[] mByte = new byte[size]; //cria um vetor de bytes com o tamanho do registro
                      acess.seek(lapPosition); 
                      acess.read(mByte);
                      obj.fromByteArray(mByte); //cria os atributos do objetos com o vetor de bytes
				}
			} 
			
		}
		return obj; //retorna o objeto caso encontrado ou retorna null caso não tenha sido encontrado
	}
  
	public void update(T newObj) throws Exception {
		boolean flag = false;
		byte[] newByte = newObj.toByteArray();
		if(newObj.getID() < 0) System.out.println("Registro Inválido"); 
		else {
			acess.seek(4); //posiciona o ponteiro no primeiro registro após o cabeçalho
            long lapPosition;
            boolean lap;
            short size;
            while (!flag) { //Lê o registro, verifica se a lapide é verdadeira e o registro tem o mesmo id passado para o método
            	lapPosition = acess.getFilePointer();
            	lap = acess.readBoolean(); 
            	size = acess.readShort();
            	byte[] mByte = new byte[size]; 
            	acess.read(mByte);
            	T obj = constructor.newInstance();
            	obj.fromByteArray(mByte);
            	if (acess.getFilePointer() >= acess.length()) flag =  true;
            	if(lap && newObj.getID() == obj.getID()){ 
            		acess.seek(lapPosition);                    
            		acess.read(mByte); 
            		if(newByte.length <= mByte.length){ //verifica se o novo registro é tem um tamanho menor ou igual ao registro que vai ser atualizado
            			acess.seek(lapPosition);
            			acess.writeBoolean(true); 
            			acess.writeShort(mByte.length); 
	                	acess.write(newByte);
					} else {
						acess.seek(lapPosition); //posiciona o ponteiro na posicao do lapide
						acess.writeBoolean(false); //escreve o lapide como falso (excluido)
						acess.seek(acess.length()); //posiciona o ponteiro no final do arquivo
						acess.writeBoolean(true); //escreve o lapide como verdadeiro e em seguida escreve o registro
						acess.writeShort(newByte.length); 
						acess.write(newByte); 
					} 
            		flag = true;
            	}
            }
            
		}
      
  }
  
	public boolean delete(int id) throws Exception {
		boolean returnDel = false;
		if(id <= 0) System.out.println("\nREGISTRO INVÁLIDO"); 
		else {
            acess.seek(4); //posiciona o ponteiro no primeiro registro após o cabeçalho
            long lapidePos;
            boolean lap;
            long currentLap;
            boolean flag = false;
            short size;
            int objID;
            while(!flag){ //lê o registro, verifica se a lapide é verdadeira e o registro tem o mesmo id passado para o método
                lapidePos = acess.getFilePointer(); 
                lap = acess.readBoolean();
                size = acess.readShort();
                currentLap = acess.getFilePointer();
                objID = acess.readInt(); 
                acess.seek(currentLap + size); //posiciona o ponteiro nas lapides dos próximos registros 
                if(acess.getFilePointer()>=acess.length()) flag = true; 
                    if(lap && id == objID) { 
                        flag = true;
                        acess.seek(lapidePos); 
                        acess.writeBoolean(false); 
                        returnDel = true; 
                    }
            }   
		}
        return returnDel;
	}
  
  

}