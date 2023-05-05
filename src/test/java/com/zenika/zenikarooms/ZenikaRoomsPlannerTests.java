package com.zenika.zenikarooms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ZenikaRoomsPlannerTests {
	private final Calculator underTest=new Calculator();
	@Test
	void itShouldAddTwoNumbers() {
		// Given
		int num1=10;
		int num2=20;
		// When
		int result=underTest.add(num1, num2);
		// Then
		int expected=30;
		assertThat(result).isEqualTo(expected);
	}

	class Calculator{

		public int add(int num1, int num2){
			return num1+num2;
		}
	}

}
