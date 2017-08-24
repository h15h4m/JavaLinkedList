//h15h4m
// just a basic Linked List implementation.

import java.util.NoSuchElementException;


public class LinkedList
{
   //nested class to represent a node
   private class Node
   {
          public Object data;
          public Node next;
   }

   //only instance variable that points to the first node.
   private Node first;

   // Constructs an empty linked list.
   public LinkedList()
   {
      first = null;
   }


   // Returns the first element in the linked list.
   public Object getFirst()
   {
      if (first == null)
       {
         NoSuchElementException ex
             = new NoSuchElementException();
         throw ex;
       }
      else
         return first.data;
   }

   // Removes the first element in the linked list.
   public Object removeFirst()
   {
      if (first == null)
       {
         NoSuchElementException ex = new NoSuchElementException();
         throw ex;
       }
      else
       {
         Object element = first.data;
         first = first.next;  //change the reference since it's removed.
         return element;
       }
   }

   // Adds an element to the front of the linked list.
   public void addFirst(Object element)
   {
      //create a new node
      Node newNode = new Node();
      newNode.data = element;
      newNode.next = first;
      //change the first reference to the new node.
      first = newNode;
   }

   // Returns an iterator for iterating through this list.
   public ListIterator listIterator()
   {
      return new LinkedListIterator();
   }


   /*********************************************************
   Add your methods here
   *********************************************************/
   
   
   
   // This method is going to concatenate the strings in the linked list
   // and then it is going to return a string
   // e.g. { Apple Banana Orange}
   public String toString() {
	   
	   // This is going to be the returned string.
	   String result = "{ ";
	   
	   // This object will make us able to move in the linked list.
	   LinkedListIterator iterator = new LinkedListIterator();
	   
	   try {
		   
		   // while it list has a next element.
		   while(iterator.hasNext() )
		   {
			   // getting those strings.
			   result += iterator.next()+ " ";
		   }
	   } 
	   
	   // When I try to list when there is no element in the list the program crashes.
	   catch (NoSuchElementException e) {
		   System.out.println("No such element.");
	   }
	   
	   // ending the final string.
	   result += "}\n";
	   
	   
	   return result;
   }
   
   
   
   // This method is going to check if the linked list is empty or not.
   // true when empty, and false when not.
   public boolean isEmpty() {
	   
	   // so we can move in the linked lists.
	   LinkedListIterator iterator = new LinkedListIterator();
	   
	   // if no value: true.
	   // if value : false
	   if (!iterator.hasNext())
		   return true;
	   else
		   return false;
	   
   }
   
   
   
   
   // This is an additional method to find the size of the linked list.
   // this is going to be useful since we are going to check the size of
   // list in the other methods.
   public int listSize() {
	   
	   // This object will make us get in the linked list.
	   // we will need this in order to count the elements.
	   LinkedListIterator iterator = new LinkedListIterator();
	   
	   // counter
	   int i = 0;
	   
	   // if it has next. move to the next, and count.
	   while (iterator.hasNext())
	   {
		   iterator.next();
		   i++;
	   }
	   
	   // returning the count.
	   return i;
   }
   
   
   
   // This method will add the parameter to the linked list in alphabetical order.
   public void addElement(Object element) {	  
	   
	   // This is going to make move in the list.
	   ListIterator iterator = listIterator();
	   
	   
	   // This variable will make it easier for us to 
	   // know how to add the element later.
	   // true : make this element the last one in the list
   	   boolean insertelementinLast = true;    
   	   
   	   // This variable will contain the current element value.
   	   String currentelementValue = "";
   	   
   	   // while there is a next element.
   	   while ( iterator.hasNext( ) ) {
   		   
   		   // contain the current value.
   		   currentelementValue = (String) iterator.next();    	
   		   
   		   // using compareTo() method in order to compare the strings alphabetically.
   		   // We are going through each element until the new element is bigger(alphabetically)
   		   // than the passed element, then we will know that is the place to add the new element.
   		   if (currentelementValue.compareTo(element.toString()) > 0) {
   			   
   			   // we will know now that we won't be able to add this element 
   			   insertelementinLast = false;
   			   
   			   // breaking the loop.
   			   break;
   		   }    		
   	   }

   	   
   	   // when there is no elements in the list, or when the list
   	   // is not empty, but the new element will be added to the end.
   	   if ( isEmpty() || insertelementinLast ) 
   	   {
   		   iterator.add(element);     		
   	   }
   	   
   	   
   	   // The linked list is not empty in all cases
   	   // add the new element to the beginning of the list.
   	   // or add the element somewhere in the middle.
   	   else
   	   {
   		   iterator.set(element);
   		   
   		   iterator.add(currentelementValue);
   	   }
  }
   
   
   
   // This method is going to remove the element in the selected index number
   // and return it.
   public Object removeElement(int index)
   {
	  
	   
	   // this is going to contain the return value.
	   Object result = null;

	   
	   // to access elements in the linked list.
	   LinkedListIterator iterator = new LinkedListIterator();
	   
	   // checking our boundaries, so we throw the appropriate exception.
	   // In other words, making sure that the index parameter does not
	   // be less than 0 and not more than the list size.
	   if(index < 0) {
		   IndexOutOfBoundsException ex = new IndexOutOfBoundsException();
		   throw ex;
	   }

	   if(index > listSize() - 1) {
		   IndexOutOfBoundsException ex = new IndexOutOfBoundsException();
		   throw ex;
	   }
	   
	   // getting the first element at the specified index.
	   result = getElement(index);
	   
	   // checking if the index is less than list size
	   if( index < listSize() - 1 )
	   {
		   // moving toward the index 
		   for( int i = 0 ; i < index + 1; i++ )
		   {
			   // go the next.
			   iterator.next();
		   }
		   
		   
		   while( index < listSize() - 2)
		   {
			   // setting a value in the current index from the next one.
			   iterator.set(getElement(index + 1));
			   
			   // go to the next
			   iterator.next();
			   
			   // add 1
			   index++;
		   }
		   
		   // remove the current.
		   iterator.remove();
	   }
	   
	   
	   else
	   {
		   for(  int i = 0 ; i < index + 1 ; i++   )
		   {
			   // go to the next
			   iterator.next();
		   }
		   
		   
		   // remove the current.
		   iterator.remove();
	   }
	   

	   
	   // return final result.
	   return result;
}
   
   // This method is going to search for the string at the parameter index
   // and returns it. The indexing is like any other array; starts from 0.
   public Object getElement(int index) {
	   
	   // to move in the list
	   LinkedListIterator iterator = new LinkedListIterator();
	   
	   
	   // checking the bounds
	   
	   if (index < 0)
	   {
		   IndexOutOfBoundsException e = new IndexOutOfBoundsException();
		   throw e;
	   }
	   
	   if (index > listSize() - 1) {
		   IndexOutOfBoundsException e = new IndexOutOfBoundsException();
		   throw e;
	   }
	   
	   // we are going to stop right before the desired index.
	   for (int i = 0 ; i < index ; i++)
		   iterator.next(); // move to the next.
	   
	   // return the next one.
	   return iterator.next();
	   
   }
   
   
   
   // This searches for the oldString in the list and then replaces them
   // with the second string newString. (All Occurrences).
   public void searchAndReplace(Object oldString, Object newString) {
	   
	   // so we move.
	   LinkedListIterator iterator = new LinkedListIterator();
	   
	   while (iterator.hasNext()) 
	   {
		   // if the string in the array equals the oldString that
		   // we are looking for.
		   if ((iterator.next()).equals((String) oldString))
		   {
			   // set the value of this index with newString.
			   iterator.set(newString);
		   }
		   
	   }
	   
	   
   }

   
   
   // This method searches for the parameter string with the largest index, and returns
   // its value. If the string does not exist returns -1.
   public int indexOfLast(Object searchString) {
	   
	   
	   LinkedListIterator iterator = new LinkedListIterator();
	   
	   //
	   int i = 0;
	   
	   // this will not change if we didn't find the string 
	   // we are looking for.
	   int lastIndex = -1;
	   
	   while (iterator.hasNext()) 
	   {
		   // checking if the string values in the index equals the parameter.
		   if ((iterator.next()).equals((String) searchString))
		   {
			   // saving the last index.
			   lastIndex = i;
		   }
		   
		   // add 1 to i.
		   i++;
	   }
	   
	   
	   // return.
	   return lastIndex;
   }
   
   
   
   
   
   //nested class to define its iterator
   private class LinkedListIterator implements ListIterator
   {
      private Node position; //current position
      private Node previous; //it is used for remove() method

      // Constructs an iterator that points to the front
      // of the linked list.

      public LinkedListIterator()
      {
         position = null;
         previous = null;
      }

     // Tests if there is an element after the iterator position.
     public boolean hasNext()
      {
         if (position == null) //not traversed yet
          {
             if (first != null)
                return true;
             else
                return false;
          }
         else
           {
              if (position.next != null)
                 return true;
              else
                 return false;
           }
      }

      // Moves the iterator past the next element, and returns
      // the traversed element's data.
      public Object next()
      {
         if (!hasNext())
          {
           NoSuchElementException ex = new NoSuchElementException();
           throw ex;
          }
         else
          {
            previous = position; // Remember for remove

            if (position == null)
               position = first;
            else
               position = position.next;

            return position.data;
          }
      }

      // Adds an element before the iterator position
      // and moves the iterator past the inserted element.
      public void add(Object element)
      {
         if (position == null) //never traversed yet
         {
            addFirst(element);
            position = first;
         }
         else
         {
            //making a new node to add
            Node newNode = new Node();
            newNode.data = element;
            newNode.next = position.next;
            //change the link to insert the new node
            position.next = newNode;
            //move the position forward to the new node
            position = newNode;
         }
         //this means that we cannot call remove() right after add()
         previous = position;
      }

      // Removes the last traversed element. This method may
      // only be called after a call to the next() method.
      public void remove()
      {
         if (previous == position)  //not after next() is called
          {
            IllegalStateException ex = new IllegalStateException();
            throw ex;
          }
         else
          {
           if (position == first)
            {
              removeFirst();
            }
           else
            {
              previous.next = position.next; //removing
            }
           //stepping back
           //this also means that remove() cannot be called twice in a row.
           position = previous;
      }
      }

      // Sets the last traversed element to a different value.
      public void set(Object element)
      {
         if (position == null)
          {
            NoSuchElementException ex = new NoSuchElementException();
            throw ex;
          }
         else
          position.data = element;
      }
   } //end of LinkedListIterator class
} //end of LinkedList class
