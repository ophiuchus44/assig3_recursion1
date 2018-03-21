// CS 0445 Spring 2018
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration is allowed in this implementation. 

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.


	// Create a new empty MyStringBuilder2
	public MyStringBuilder2()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}


	// Create a new MyStringBuilder2 initialized with the chars in String s
	public MyStringBuilder2(String s)
	{
		if (s != null && s.length() > 0){
			makeBuilderSTR(s, 0);
			CNode temp = firstC;
			
		}

      else  // no String so initialize empty MyStringBuilder2

      {

            length = 0;

            firstC = null;

            lastC = null;

      }

     // System.out.println("LastC string constructor?!? " +lastC.data);
	}


	private void makeBuilderSTR(String s, int pos)

	{

      // Recursive case – we have not finished going through the String

      if (pos < s.length()-1)

      {

            // Note how this is done – we make the recursive call FIRST, then

            // add the node before it.  In this way the LAST node we add is

            // the front node, and it enables us to avoid having to make a

            // special test for the front node.  However, many of your

            // methods will proceed in the normal front to back way.

            makeBuilderSTR(s, pos+1);

            firstC = new CNode(s.charAt(pos), firstC);

            length++;

      }

      else if (pos == s.length()-1) // Special case for last char in String

      {                             // This is needed since lastC must be

                                    // set to point to this node

            firstC = new CNode(s.charAt(pos));

            lastC = firstC;

            length = 1;

      }

      else  // This case should never be reached, due to the way the

            // constructor is set up.  However, I included it as a

      {     // safeguard (in case some other method calls this one)

            length = 0;

            firstC = null;

            lastC = null;

      }

}




	// Create a new MyStringBuilder2 initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if (s != null && s.length > 0){

            makeBuilderCHARARRAY(s, 0);
        }
      else  // no String so initialize empty MyStringBuilder2

      {

            length = 0;

            firstC = null;

            lastC = null;

      }

      //System.out.println("lastC Char constructor " + lastC.data);


	}


	private void makeBuilderCHARARRAY(char [] s, int pos)

	{

      // Recursive case – we have not finished going through the String

      if (pos < s.length-1)

      {

            // Note how this is done – we make the recursive call FIRST, then

            // add the node before it.  In this way the LAST node we add is

            // the front node, and it enables us to avoid having to make a

            // special test for the front node.  However, many of your

            // methods will proceed in the normal front to back way.

            makeBuilderCHARARRAY(s, pos+1);

            firstC = new CNode(s[pos], firstC);

            length++;

      }

      else if (pos == s.length-1) // Special case for last char in String

      {                             // This is needed since lastC must be

                                    // set to point to this node

            firstC = new CNode(s[pos]);

            lastC = firstC;

            length = 1;

      }

      else  // This case should never be reached, due to the way the

            // constructor is set up.  However, I included it as a

      {     // safeguard (in case some other method calls this one)

            length = 0;

            firstC = null;

            lastC = null;

      }


	}



	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{

		CNode temp = firstC;
			
		if(firstC != null && length > 0){			
		
			CNode newNode = b.firstC;
			appendMyStrBld(b, newNode);	
		}
		
		else{
			firstC = new CNode(b.firstC.data);
			lastC = firstC;
			CNode newNode = b.firstC.next;

			appendMyStrBld(b, newNode);
						

		}


		return this;
	}

	private void appendMyStrBld(MyStringBuilder2 b, CNode newNode){

		if(newNode.next!=null){

			lastC.next = new CNode(newNode.data);
			
			lastC = lastC.next;
			//newNode = newNode.next;

			length++;

			appendMyStrBld(b,newNode.next);			

		}
		else{
			CNode temp = new CNode(newNode.data);
			lastC.next = temp;

			lastC = temp;
			length++;


		}
						
		
				
	}


	public MyStringBuilder2 append(String s)
	{
		if(firstC != null && length > 0){	
			appendString(s, 0);
			length += s.length();
		}

		
		else{

			firstC = new CNode(s.charAt(0));
			lastC = firstC;
			appendString(s, 1);
			length += s.length();		
		}
		return this;
	}

private void appendString(String s, int pos){

		if(pos<s.length()-1){		
			lastC.next = new CNode(s.charAt(pos));
			lastC = lastC.next;
			appendString(s,pos+1);	

		}
		else{
			lastC.next = new CNode(s.charAt(pos));
			lastC = lastC.next;

		}
	}				
		






	// Append char array c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c)
	{
		
		if(firstC != null && length > 0){	
		
			CNode newNode = new CNode(c[0]);
			appendCharArray(c, newNode, 0);
		}

		
		else{
			CNode newNode = firstC;
			appendCharArray(c, newNode, 0);
			
		}


		return this;
	}




	private void appendCharArray(char [] s, CNode newNode, int pos){

		if(pos<s.length-1){				

			newNode = new CNode(s[pos]);
			lastC.next = newNode;

			lastC = newNode;

			length++;
			
			appendCharArray(s,newNode, pos+1);			

		}
		else{

			lastC.next = new CNode(s[pos]);
			
			lastC = lastC.next;

			length++;
		}
						

	}

	// Append char c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{
		
		if(firstC != null && length > 0){
			CNode newNode = new CNode(c);

			lastC.next = newNode;
			lastC = newNode;
			length++;

		}
		else{
		
			firstC = new CNode(c);
			lastC = firstC;
			length = 1;			
			}

		return this;



	}

	// Return the character at location "index" in the current MyStringBuilder2.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index) throws IndexOutOfBoundsException
	{
		if(index>length){
			throw new IndexOutOfBoundsException();
		}
		CNode curr = firstC;
		char c = getCharAt(index,0, curr);
		return c;
	}


	private char getCharAt(int index, int pos, CNode curr){
		//CNode curr = firstC;
		return (index == pos) ? curr.data : getCharAt(index, pos+1, curr.next);
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder2, and return the current MyStringBuilder2.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
	// only remove up until the end of the MyStringBuilder2. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end)
	{

		if(start > length || start < 0 || end <= start){
			return this;
		}
		else{
			CNode curr = firstC;
			CNode startNode = firstC;
			CNode endNode = lastC;

			if(end>=length){
				end= length;
			}

			deleteIt(curr, startNode, endNode, 0, start, end);


		}
		int tmp = end - start;

		length -= tmp;

		
		return this;
	}


	private void deleteIt(CNode curr, CNode startNode, CNode endNode, int pos, int start, int end){
		

		if(pos == (start -1)){
			startNode = curr;

//			System.out.println("startNode is = " + startNode.data);
//			System.out.println("currNode1 is = " + curr.data);
//			System.out.println("pos is = " + pos);

			if(end == length){
				//System.out.println("currNode1 is = " + curr.data);
				lastC = startNode;
				lastC.next = null;
				}

			else{
				curr = curr.next;
				deleteIt(curr, startNode, endNode, pos+1, start, end);
			}
		}
		else if(pos == end){
//			System.out.println("currNode2 is = " + curr.data);
			endNode = curr;
			
			if(end == length){
				lastC = curr;

				}

			if(start == 0){
				firstC = endNode;
			}
			else{
				startNode.next = endNode;	
			}
			


		}
		else if(curr.next != null){
//				System.out.println("currNode is = " + curr.data);
//				System.out.println("pos is = " + pos);

			curr = curr.next;
			deleteIt(curr, startNode, endNode, pos+1, start, end);
		}
		
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder2 as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{
		if(index == 0){
			return this.delete(index, index+1);			
		}

		else if(index<length){
			return this.delete(index, index+1);	
		}

		else if(index >length){
			return this;	
		}

		return this;
	}


	// Find and return the index within the current MyStringBuilder2 where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.

	public int indexOf(String str)
	{
		//System.out.println(" == SEARCHING FOR NEW WORD === " + str);
		CNode curr = firstC;
		int result = indexOfR(str, curr, 0);

		return result;
	}

private int indexOfR(String str, CNode curr, int pos){	
	int result = pos;
	
	if(str.charAt(0) == curr.data){
		//System.out.println("INDEX SEARCHING " + pos);
		if(foundWord(str, 1, curr.next)){
		//	System.out.println("FOUND WORD! == ");
			return result;
		}
	}
	if(pos<length-1){
		
		return indexOfR(str, curr.next, pos+1);	
	}
	return -1;

}


	
	private boolean foundWord(String str, int count, CNode curr){

		if(count < str.length() && curr != null){

			if(str.charAt(count) == curr.data){
		
				if(count == str.length()-1){

					return true;	
				}
			}

			return foundWord(str, count+1, curr.next);

		}
		else{
			return false;
		}
		//return;
	}


	// Insert String str into the current MyStringBuilder2 starting at index
	// "offset" and return the current MyStringBuilder2.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
	{
		if(offset == 0){
			insertStrStart(str.length()-1, str);
		}
		else if(offset>0 && offset <length){
			CNode curr = firstC;
			CNode strStartNode = curr;
			CNode strEndNode= curr;
			insertStrOffset(curr, strStartNode, strEndNode, 0, str, offset);

		}
		else if(offset > length || offset < 0){
			return this;
		}

		else if(offset == length){
			return this.append(str);
		}
		
		return this;
	}

	private void insertStrOffset(CNode curr, CNode strStartNode, CNode strEndNode, int pos, String str, int offset){
			
	//	System.out.println("current data == " + curr.data);
		if(pos<offset-1){
			insertStrOffset(curr.next, strStartNode, strEndNode, pos+1, str, offset);
			
		}
		else{
	//		System.out.println("set start node == " + curr.data);
			strStartNode = curr;
			insertStrOffsetInsert(curr, strEndNode, 0, str);
		}

	}

	private void insertStrOffsetInsert(CNode curr, CNode strEndNode, int pos, String str){

		if(pos<str.length()){
			char c = str.charAt(pos);
			CNode newNode = new CNode(c);
			newNode.next = curr.next;
			curr.next = newNode;
			curr = newNode;
			length++;
			insertStrOffsetInsert(curr, strEndNode, pos+1, str);	
			strEndNode = curr;
		}
		
	}

	private void insertStrStart(int pos, String str){
		
		if(pos>=0){
			char c = str.charAt(pos);
			CNode newNode = new CNode(c);
			newNode.next = firstC;
			firstC = newNode;
			length++;
			insertStrStart(pos-1, str);	
		}
		
	}


	// Insert character c into the current MyStringBuilder2 at index
	// "offset" and return the current MyStringBuilder2.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.


	public MyStringBuilder2 insert(int offset, char c){
			if(offset == 0){
				insertCharStart(1, c);
			}
			else if(offset>0 && offset <length){
				CNode curr = firstC;
				CNode strStartNode = curr;
				CNode strEndNode= curr;
				insertCharOffset(curr, strStartNode, strEndNode, 0, c, offset);

		}
		else if(offset > length || offset < 0){
			return this;
		}

		else if(offset == length){
			return this.append(c);
		}
		
		return this;
	}

	private void insertCharOffset(CNode curr, CNode strStartNode, CNode strEndNode, int pos, char c, int offset){
			
		//System.out.println("current data == " + curr.data);
		if(pos<offset-1){
			insertCharOffset(curr.next, strStartNode, strEndNode, pos+1, c, offset);
			
		}
		else{
		//	System.out.println("set start node == " + curr.data);
			strStartNode = curr;
			insertCharOffsetInsert(curr, strEndNode, 0, c);
		}

	}

	private void insertCharOffsetInsert(CNode curr, CNode strEndNode, int pos, char c){

		//if(pos<str.length()){
			//char c = str.charAt(pos);
			CNode newNode = new CNode(c);
			newNode.next = curr.next;
			curr.next = newNode;
			curr = newNode;
			length++;
			//insertStrOffsetInsert(curr, strEndNode, pos+1, str);	
			strEndNode = curr;
		//}
		
	}

	private void insertCharStart(int pos, char c){
		
		//if(pos>=0){
			CNode newNode = new CNode(c);
			newNode.next = firstC;
			firstC = newNode;
			length++;
		//	insertStrStart(pos-1, str);	
		//}
		
	}

	// Insert char array c into the current MyStringBuilder2 starting at index
	// index "offset" and return the current MyStringBuilder2.  If "offset" is
	// invalid, do nothing.

	public MyStringBuilder2 insert(int offset, char [] c)
	{
		if(offset == 0){
			insertCharArStart(c.length-1, c);
		}
		else if(offset>0 && offset <length){
			CNode curr = firstC;
			CNode strStartNode = curr;
			CNode strEndNode= curr;
			insertCharArOffset(curr, strStartNode, strEndNode, 0, c, offset);

		}
		else if(offset > length || offset < 0){
			return this;
		}

		else if(offset == length){
			return this.append(c);
		}
		
		return this;
	}

	private void insertCharArOffset(CNode curr, CNode strStartNode, CNode strEndNode, int pos, char [] c, int offset){
			
		//System.out.println("current data == " + curr.data);
		if(pos<offset-1){
			insertCharArOffset(curr.next, strStartNode, strEndNode, pos+1, c, offset);
			
		}
		else{
		//	System.out.println("set start node == " + curr.data);
			strStartNode = curr;
			insertCharArOffsetInsert(curr, strEndNode, 0, c);
		}

	}

	private void insertCharArOffsetInsert(CNode curr, CNode strEndNode, int pos, char [] d){

		if(pos<d.length){
			//char c = str.charAt(pos);
			char c = d[pos];
			CNode newNode = new CNode(c);
			newNode.next = curr.next;
			curr.next = newNode;
			curr = newNode;
			length++;
			insertCharArOffsetInsert(curr, strEndNode, pos+1, d);	
			strEndNode = curr;
		}
		
	}

	private void insertCharArStart(int pos, char [] d){
		
		if(pos>=0){
			//char c = str.charAt(pos);
			char c = d[pos];

			CNode newNode = new CNode(c);
			newNode.next = firstC;
			firstC = newNode;
			length++;
			insertCharArStart(pos-1, d);	
		}
		
	}

	// Return the length of the current MyStringBuilder2
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder2, then insert String "str" into the current
	// MyStringBuilder2 starting at index "start", then return the current
	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder2, only delete until the
	// end of the MyStringBuilder2, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder2 replace(int start, int end, String str)
	{
		if(start > length || start < 0 || end <=start){
			return this;
		}
		else if (start>0 || end<length){

			// if end is greater than length, set end to length
			if(end>=length){
				end= length;
			}
		//	System.out.println("length1 == " + length);

			CNode curr = firstC;
			CNode startNode = firstC;
			CNode endNode = lastC;
		//	System.out.println("about to recurse");

			replaceRec(curr, startNode, endNode, 0, str, start, end);

		} // end of replace
		return this;
	}

	private void replaceRec(CNode curr, CNode startNode, CNode endNode, int pos, String str, int start, int end){

		if(pos==(start -1)){

			startNode = curr;

			replaceRec(curr.next, startNode, endNode, pos+1, str, start, end );
			
		}
		else if(pos == end){
			endNode = curr;
		
			if(end != length){
				//lastC = curr;
				//System.out.println("end != length? ");
				MyStringBuilder2 tmp = new MyStringBuilder2(str);
				startNode.next= tmp.firstC;
				tmp.lastC.next = endNode;
				length+=tmp.length - (end-start);	
			}
			else if (end == length){

				//System.out.println("end == length? ");
				MyStringBuilder2 tmp = new MyStringBuilder2(str);
				startNode.next= tmp.firstC;
				lastC = tmp.lastC;
				length+=tmp.length - (end-start);	

			}

		}
		else if (curr.next !=null){
			replaceRec(curr.next, startNode, endNode, pos+1, str, start, end );
		}
		else if (curr != null){
			replaceRec(curr.next, startNode, endNode, pos+1, str, start, end );
		}

	}

	// Reverse the characters in the current MyStringBuilder2 and then
	// return the current MyStringBuilder2.
	public MyStringBuilder2 reverse()
	{
		lastC = reverseIt(firstC);
		return this;
	}

	private CNode reverseIt(CNode curr){
		CNode tmp;
		if(curr.next != null){
			tmp = reverseIt(curr.next);
			curr.next = null;
			tmp.next = curr;
			return curr;
		}
		else{
			firstC = curr;
			return curr;
		}
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end)
	{
		char [] c = new char[end-start];

	//	System.out.println("length of char array == " + c.length);
		//int charCount = 0;

		if(start > length || start < 0 || end <=start){
			return "nothing found";
		}
		else if(start == 0){
			CNode curr = firstC;
			substringRecFRONT(end, curr, 0, c);

		}
		else if(start>0 || end<length){

			// if end is greater than length, set end to length
			if(end>=length){
				end= length;
			}

			CNode curr = firstC;
			CNode startNode = firstC;
			CNode endNode = lastC;
			int counter = 0;
			//System.out.println("About to search middle");
			substringRecMIDDLE(start, end, curr, startNode, endNode,0, c, counter);

		}

		return String.valueOf(c);
	}


	private void substringRecFRONT(int end, CNode curr, int pos, char [] c){
		if(pos<end){
			c[pos] = curr.data;
			substringRecFRONT(end, curr.next, pos+1, c);	
		}
	}


	private void substringRecMIDDLE(int start, int end, CNode curr, CNode startNode, CNode endNode, int pos, char [] c, int counter){
		//System.out.println("pos1 = " + pos);
		//System.out.println("start1 = " + start);
		if(pos== start){
			//startNode = curr;
		//	System.out.println("SHOULD ONLY RUN ONCE");
			c[0] = curr.data;
			substringRecMIDDLE(start, end, curr.next, startNode, endNode, pos+1, c, counter+1);
		}
		else if(pos > start && pos < end){
			//endNode = curr;
			c[counter] = curr.data;
			substringRecMIDDLE(start, end, curr.next, startNode, endNode, pos+1, c, counter+1);
		}
		else if (pos<end){
			substringRecMIDDLE(start, end, curr.next, startNode, endNode, pos+1, c, 0);	
		}
	}

	// Return the entire contents of the current MyStringBuilder2 as a String
	public String toString()
	{
		char [] c = new char[length];

      	getString(c, 0, firstC);

      	return (new String(c));
	}

	private void getString(char [] c, int pos, CNode curr)

	{

      if (curr != null)

      {      		
            c[pos] = curr.data;
            getString(c, pos+1, curr.next);

      }

	}

	// Find and return the index within the current MyStringBuilder2 where
	// String str LAST matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.  For some
	// help with this see the Assignment 3 specifications.
	public int lastIndexOf(String str)
	{
		CNode curr = firstC;
		int result = lastIndexOfRec(str, curr, 0);
		return result;
	}

	private int lastIndexOfRec(String str, CNode curr, int pos){
		//System.out.println("currNode == " + curr.data);
		if(curr.next == null){
			return -1;
		}
		int result = lastIndexOfRec(str, curr.next, pos+1);	
		
		if(result >= 0) {	
			return result;
		}
		if(str.charAt(0) == curr.data){
		
			if(foundWord(str, 1, curr.next)) {
				return pos;
			}
		}
		return -1;
	}
	
	// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
	// in the return array corresponds to a part of the match of the array of
	// patterns in the argument.  If the overall match does not succeed, return
	// null.  For much more detail on the requirements of this method, see the
	// Assignment 3 specifications.
/*	public MyStringBuilder2 [] regMatch(String [] pats)
	{

		System.out.println("number of patterns = " + pats.length); 



		this[counter] = regMatch(pats, this)

		return null;
	}


	private void regMatchREC(String [] pats, MyStringBuilder2 [], int pos){

	}
*/

	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder2 class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}
