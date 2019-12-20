Introduction:

	In this project you will add methods to an existing linked list class

Description:

Modify the author's(Mark Allen Weiss) "MyLinkedList" class to add the following methods:
	
	a) swap
	    receives two index positions as parameters, and swaps the nodes at
			these positions by changing the links, provided both positions are 
			within the current size
	
	b) shift
	    receives an integer (positive or negative) and shifts the list this
			many positions forward (if positive) or backward (if negative).  
			1,2,3,4    shifted +2    3,4,1,2
			1,2,3,4    shifted -1    4,1,2,3
	
	c) erase 
		receives an index position and number of elements as parameters, and
			removes elements beginning at the index position for the number of 
			elements specified, provided the index position is within the size
			and together with the number of elements does not exceed the size
	
	d) insertList
		receives another MyLinkedList and an index position as parameters, and 
			copies the list from the passed list into the list at the specified
			position, provided the index position does not exceed the size.
	
	e) main
		add code to the main method to demonstrate each of your methods