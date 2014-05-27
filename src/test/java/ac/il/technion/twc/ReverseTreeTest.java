package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ac.il.technion.twc.impl.ReverseTree;

public class ReverseTreeTest
{

	@Test
	public void testReverseTree()
	{
		final ReverseTree<String> $ = new ReverseTree<>();
		assertNotNull($);
		assertEquals(0, $.size("s"));
	}

	@Test
	public void testPut()
	{
		final ReverseTree<String> $ = new ReverseTree<>();
		$.put("1", "2");
		assertEquals(1, $.size("1"));
		assertEquals(0, $.size("2"));
	}

	@Test
	public void testChainThree()
	{
		final ReverseTree<String> $ = new ReverseTree<>();
		$.put("1", "2");
		$.put("2", "3");
		assertEquals(2, $.size("1"));
		assertEquals(0, $.size("2"));
		assertEquals(0, $.size("3"));
	}

	@Test
	public void testChainTree()
	{
		final ReverseTree<String> $ = new ReverseTree<>();
		$.put("1", "2");
		$.put("1", "3");
		assertEquals(2, $.size("1"));
		assertEquals(0, $.size("2"));
		assertEquals(0, $.size("3"));
	}

	@Test
	public void testChainFour()
	{
		final ReverseTree<String> $ = new ReverseTree<>();
		$.put("1", "2");
		$.put("2", "3");
		$.put("3", "4");
		assertEquals(3, $.size("1"));

	}

	@Test
	public void testChainFiveTree()
	{
		final ReverseTree<String> $ = new ReverseTree<>();
		$.put("1", "2");
		$.put("2", "3");
		$.put("3", "4");
		$.put("1", "5");
		assertEquals(4, $.size("1"));
		assertEquals(0, $.size("4"));
		assertEquals(0, $.size("5"));
	}

	@Test
	public void testChainFullTreeHeightTwo()
	{
		final ReverseTree<String> $ = new ReverseTree<>();
		$.put("1", "2");
		$.put("1", "3");
		$.put("2", "4");
		$.put("2", "5");
		$.put("3", "6");
		$.put("3", "7");
		assertEquals(6, $.size("1"));
		assertEquals(0, $.size("5"));
		assertEquals(0, $.size("6"));
		assertEquals(0, $.size("7"));
	}

	@Test
	public void addChains()
	{
		final ReverseTree<String> $ = new ReverseTree<>();
		$.put("1", "2");
		$.put("3", "4");
		$.put("2", "3");
		assertEquals(3, $.size("1"));
	}

	@Test
	public void addFourChains()
	{
		final ReverseTree<Integer> $ = new ReverseTree<>();
		$.put(1, 2);
		$.put(2, 3);
		$.put(1, 4);
		$.put(5, 6);
		$.put(7, 8);
		$.put(9, 10);
		assertEquals(1, $.size(5));
		assertEquals(3, $.size(1));
		assertEquals(1, $.size(9));
		assertEquals(1, $.size(7));
		$.put(4, 5);
		assertEquals(5, $.size(1));
		$.put(3, 7);
		assertEquals(7, $.size(1));
		$.put(2, 9);
		assertEquals(9, $.size(1));
	}

	@Test(timeout = 2)
	public void addFourChainAsChain()
	{
		final ReverseTree<Integer> $ = new ReverseTree<>();
		$.put(1, 2);
		$.put(2, 3);
		$.put(1, 4);
		$.put(5, 6);
		$.put(7, 8);
		$.put(9, 10);
		assertEquals(1, $.size(5));
		assertEquals(3, $.size(1));
		assertEquals(1, $.size(9));
		assertEquals(1, $.size(7));
		$.put(6, 7);
		assertEquals(3, $.size(1));
		assertEquals(3, $.size(5));
		$.put(8, 9);
		assertEquals(3, $.size(1));
		assertEquals(5, $.size(5));
		$.put(4, 5);
		assertEquals(9, $.size(1));
	}
	@Test
	public void addTimeOut()
	{
		final ReverseTree<Integer> $ = new ReverseTree<>();
		int num = 1_000_000;
		for(int i=0;i<num;i++)
			$.put(i, i+1);
		assertEquals(num, $.size(0));
	}

}
