package tdupec2020fa;

public class HashLinearProbing {

	private final int MAX;
	private char[] keys;
	private int[] values;
	private int elements;


	public HashLinearProbing(int MAX){
		this.MAX = MAX;
		this.elements = 0;
		keys = new char[MAX];
		values = new int[MAX];
	}

	private int hash(int key) {
		return  key % MAX;
	}

	// Put new entry and return its index if there is a place, else return -1
	public int put(char key,int value) {

		if(elements < MAX - 1) {
			int index = hash(key);
			while(keys[index] != 0) {
				index = hash(index + 1);
			}
			keys[index] = key;
			values[index] = value;
			elements++;
			return index;
		}
		return -1;
	}

	// Get the value of given key if it exists, else return -1
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

	// Remove an entry if it exists and return true, else return false
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

}
