package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    public void plus() throws Exception {
        //given
        int result = Calculator.plus(1,2);
        //when
        //then
        assertEquals(3, result);
        assertEquals(5, Calculator.plus(4,1));
    }

}