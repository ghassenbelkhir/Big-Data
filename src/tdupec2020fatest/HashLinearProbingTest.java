package tdupec2020fatest;

import org.junit.Test;

import tdupec2020fa.HashLinearProbing;

import static org.assertj.core.api.Assertions.assertThat;

public class HashLinearProbingTest {

	@Test
	public void putOneValueInMapAndCheckIndex() {
		HashLinearProbing map = new HashLinearProbing(11);

		assertThat(map.put('Z',0)).isEqualByComparingTo(2);
	}

	@Test
	public void putTwoValuesInMapAndCheckIndex() {
		HashLinearProbing map = new HashLinearProbing(11);
		
		assertThat(map.put('Z',0)).isEqualByComparingTo(2);
		assertThat(map.put('B',1)).isEqualByComparingTo(0);

	}

	@Test
	public void populateAllMapAndCheckIndex() {
		HashLinearProbing map = new HashLinearProbing(11);
		
		assertThat(map.put('Z',0)).isEqualByComparingTo(2);
		assertThat(map.put('B',1)).isEqualByComparingTo(0);
		assertThat(map.put('E',2)).isEqualByComparingTo(3);
		assertThat(map.put('K',3)).isEqualByComparingTo(9);
		assertThat(map.put('M',4)).isEqualByComparingTo(1);
		assertThat(map.put('N',5)).isEqualByComparingTo(4);
		assertThat(map.put('U',6)).isEqualByComparingTo(8);
		assertThat(map.put('L',7)).isEqualByComparingTo(10);
		assertThat(map.put('V',8)).isEqualByComparingTo(5);
		assertThat(map.put('X',9)).isEqualByComparingTo(6);
		
	}


	@Test
	public void shouldNotInsertAfterLimit() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('Z',0);
		map.put('B',1);
		map.put('E',2);
		map.put('K',3);
		map.put('M',4);
		map.put('N',5);
		map.put('U',6);
		map.put('L',7);
		map.put('V',8);
		map.put('X',9);


		assertThat(map.put('W', 10)).isEqualByComparingTo(-1);
		assertThat(map.put('I', 11)).isEqualByComparingTo(-1);
	}

	@Test
	public void getExistingKey() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('Z',0);
		assertThat(map.get('Z')).isEqualByComparingTo(0);
	}

	@Test
	public void getNotExistingKeyWithEmptyMap() {
		HashLinearProbing map = new HashLinearProbing(11);

		assertThat(map.get('T')).isEqualByComparingTo(-1);
	}

	@Test
	public void getNotExistingKeyWithSomeValuesInTheMap() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('Z',0);
		map.put('B',1);
		map.put('E',2);
		map.put('K',3);
		map.put('M',4);
		
		assertThat(map.get('V')).isEqualByComparingTo(-1);
	}
	@Test
	public void getNotExistingKeyWithFullMap() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('Z',0);
		map.put('B',1);
		map.put('E',2);
		map.put('K',3);
		map.put('M',4);
		map.put('N',5);
		map.put('U',6);
		map.put('L',7);
		map.put('V',8);
		map.put('X',9);

		assertThat(map.get('T')).isEqualByComparingTo(-1);
	}
	
	@Test
	public void removeExistingKeyWithoutRehash() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('Z',0);
		map.put('B', 2);

		assertThat(map.remove('Z')).isEqualTo(true);
		assertThat(map.get('Z')).isEqualByComparingTo(-1);
	}

	@Test
	public void removeExistingKeyAndRehash() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('Z',0);
		map.put('B',1);
		map.put('E',2);
		map.put('K',3);
		map.put('M',4);
		map.put('N',5);
		map.put('U',6);
		map.put('L',7);
		map.put('V',8);
		map.put('X',9);

		assertThat(map.getKeyByIndex(4)).isEqualByComparingTo('N');
		assertThat(map.getKeyByIndex(5)).isEqualByComparingTo('V');
		assertThat(map.remove('N')).isEqualTo(true);
		assertThat(map.get('N')).isEqualByComparingTo(-1);
		assertThat(map.getKeyByIndex(4)).isEqualByComparingTo('V');
		assertThat(map.getKeyByIndex(5)).isEqualByComparingTo('X');
	}

	@Test
	public void removeExistingKeyAndRehash2() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('Z',0);
		map.put('B',1);
		map.put('E',2);
		map.put('K',3);
		map.put('M',4);
		map.put('N',5);
		map.put('U',6);
		map.put('L',7);
		map.put('V',8);
		map.put('X',9);

		assertThat(map.getKeyByIndex(10)).isEqualByComparingTo('L');
		assertThat(map.getKeyByIndex(6)).isEqualByComparingTo('X');
		assertThat(map.remove('L')).isEqualTo(true);
		assertThat(map.get('L')).isEqualByComparingTo(-1);
		assertThat(map.getKeyByIndex(10)).isEqualByComparingTo('V');
		assertThat(map.getKeyByIndex(5)).isEqualByComparingTo('X');
	}

	@Test
	public void insertAfterRemove() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('Z',0);
		map.put('B',1);
		map.put('E',2);
		map.put('K',3);
		map.put('M',4);
		map.put('N',5);
		map.put('U',6);
		map.put('L',7);
		map.put('V',8);
		map.put('X',9);

		assertThat(map.remove('L')).isEqualTo(true);
		assertThat(map.put('Y',11)).isEqualByComparingTo(6);
	}
	
	@Test
	public void removeNotExistingKey() {
		HashLinearProbing map = new HashLinearProbing(11);
	
		assertThat(map.remove('R')).isEqualTo(false);
	}

}
