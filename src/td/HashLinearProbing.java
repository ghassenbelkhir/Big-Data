package td;

public class HashLinearProbing {

	final int MAX;
	char[] keys;
	int[] values;
	int elements;


	public HashLinearProbing(int MAX){
		this.MAX = MAX;
		this.elements = 0;
		keys = new char[MAX];
		values = new int[MAX];
	}

	private int hash(int key) {
		return (int) key % MAX;
	}

	public int put(char key,int value) {

		int index = hash(key);
		while(keys[index] != 0) {
			index = hash(index + 1);
		}

		if(elements < MAX - 1) {
			keys[index] = key;
			values[index] = value;
			elements++;
			return index;
		}
		return -1;
	}

	public int get(int key) {
		int index = hash(key);
		int valueToReturn = -1;
		while(keys[index] != key && keys[index] != 0) {
			index = hash(index + 1);
		}
		if(keys[index] == key)
			return values[index];
		return valueToReturn;

	}

	public boolean remove(char key) {
		int index = hash(key);
		while(keys[index] != key && keys[index] != 0) {
			index = hash(index + 1);
		}
		
		if(keys[index] == key) {
			keys[index] = 0;
			values[index] = 0;
			elements--;
			index = hash(index + 1);
			while(keys[index] != 0) {
				char savedKey = keys[index];
				int savedValue = values[index];
				keys[index] = 0;
				values[index] = 0;
				elements--;
				put(savedKey,savedValue);
				index = hash(index + 1);
			}
			return true;
		}
		return false;
	}

	public char getKeyByIndex(int index) {
		return keys[index];
	}

	public int getValueByIndex(int index) {
		return values[index];
	}


}
