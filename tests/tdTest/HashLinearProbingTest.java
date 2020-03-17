package tdTest;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import td.HashLinearProbing;

public class HashLinearProbingTest {

	@Test
	public void putOneValueInMapAndCheckIndex() {
		HashLinearProbing map = new HashLinearProbing(11);

		assertThat(map.put('O',1)).isEqualByComparingTo(2);
	}

	@Test
	public void putTwoValuesInMapAndCheckIndex() {
		HashLinearProbing map = new HashLinearProbing(11);
		
		assertThat(map.put('O',1)).isEqualByComparingTo(2);
		assertThat(map.put('V',4)).isEqualByComparingTo(9);

	}

	@Test
	public void populateAllMapAndCheckIndex() {
		HashLinearProbing map = new HashLinearProbing(11);
		
		assertThat(map.put('B',0)).isEqualByComparingTo(0);
		assertThat(map.put('O',1)).isEqualByComparingTo(2);
		assertThat(map.put('E',2)).isEqualByComparingTo(3);
		assertThat(map.put('P',3)).isEqualByComparingTo(4);
		assertThat(map.put('V',4)).isEqualByComparingTo(9);
		assertThat(map.put('L',5)).isEqualByComparingTo(10);
		assertThat(map.put('X',6)).isEqualByComparingTo(1);
		assertThat(map.put('N',7)).isEqualByComparingTo(5);
		assertThat(map.put('K',8)).isEqualByComparingTo(6);
		assertThat(map.put('M',9)).isEqualByComparingTo(7);
		
	}


	@Test
	public void shouldNotInsertAfterLimit() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('B',0);
		map.put('O',1);
		map.put('E',2);
		map.put('P',3);
		map.put('V',4);
		map.put('L',5);
		map.put('X',6);
		map.put('N',7);
		map.put('K',8);
		map.put('M',9);	

		map.put('W', 10);
		assertThat(map.put('M',9)).isEqualByComparingTo(-1);
		assertThat(map.get('W')).isEqualByComparingTo(-1);
	}

	@Test
	public void getExistingKey() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('B',0);
		assertThat(map.get('B')).isEqualByComparingTo(0);
	}

	@Test
	public void getNotExistingKey() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('B',0);

		assertThat(map.get('B')).isEqualByComparingTo(0);
		assertThat(map.get('T')).isEqualByComparingTo(-1);
	}

	@Test
	public void removeExistingKeyWithoutRehash() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('B',0);
		map.put('E', 2);

		assertThat(map.remove('E')).isEqualTo(true);
		assertThat(map.get('E')).isEqualByComparingTo(-1);
	}

	@Test
	public void removeExistingKeyAndRehash() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('B',0);
		map.put('O',1);
		map.put('E',2);
		map.put('P',3);
		map.put('V',4);
		map.put('L',5);
		map.put('X',6);
		map.put('N',7);
		map.put('K',8);
		map.put('M',9);	

		assertThat(map.getKeyByIndex(5)).isEqualByComparingTo('N');
		assertThat(map.getKeyByIndex(6)).isEqualByComparingTo('K');
		assertThat(map.remove('N')).isEqualTo(true);
		assertThat(map.get('N')).isEqualByComparingTo(-1);
		assertThat(map.getKeyByIndex(5)).isEqualByComparingTo('K');
		assertThat(map.getKeyByIndex(6)).isEqualByComparingTo('M');
	}

	@Test
	public void removeExistingKeyAndRehash2() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('B',0);
		map.put('O',1);
		map.put('E',2);
		map.put('P',3);
		map.put('V',4);
		map.put('L',5);
		map.put('X',6);
		map.put('N',7);
		map.put('K',8);
		map.put('M',9);	

		assertThat(map.getKeyByIndex(10)).isEqualByComparingTo('L');
		assertThat(map.getKeyByIndex(6)).isEqualByComparingTo('K');
		assertThat(map.remove('L')).isEqualTo(true);
		assertThat(map.get('L')).isEqualByComparingTo(-1);
		assertThat(map.getKeyByIndex(10)).isEqualByComparingTo('K');
		assertThat(map.getKeyByIndex(6)).isEqualByComparingTo('M');
	}

	@Test
	public void removeNotExistingKey() {
		HashLinearProbing map = new HashLinearProbing(11);
		map.put('B',0);
		assertThat(map.remove('R')).isEqualTo(false);
	}

}
