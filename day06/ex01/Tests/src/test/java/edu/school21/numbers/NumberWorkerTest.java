package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    private final NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 17, 131, 167})
    public void isPrimeForPrimes(int number) throws IllegalNumberException {
        assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 30, 224, 404, 777})
    public void isPrimeForNotPrimes(int number) throws IllegalNumberException {
        assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 0, -1, -3, -7})
    public void isPrimeForIncorrectNumbers(int number) throws IllegalNumberException {
        assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void digitSumCorrectNumbers(int inputNumber, int expectedNumber) {
        assertEquals(numberWorker.digitsSum(inputNumber), expectedNumber);
    }

}
