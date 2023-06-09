// Joy Schwarting
// 20 Questions
//
// This is file 1/2 I am turning in (the other is QuestionTree.java).
// Obviously you need the .txt files for the questions and the two 
// other .java files (QuestionMain.java and UserInterface.java) that
// you provided for this assignment, all within the same folder.
//
// QuestionNode.java: this class implements a binary tree system based
// on the users answers (yes or no).

public class QuestionNode {
      
      // data that will hold object or question
      // yes or no decides which "branch" or path
      // through tree depending on user input
      public QuestionNode yes;
      public QuestionNode no;  
      private String data;
      
      public QuestionNode(String data) {
      // this is an "answer" node that stores an answer
            this(null, null, data);
      }
      
      public QuestionNode(QuestionNode yes,QuestionNode no,String data) {
      // constructs a branch node with given data
      // this is a "question" node that stores a question
            this.yes = yes;
            this.no = no;
            this.data = data;
      } 
      
      public boolean isAnswer() { // checks if current node is an answer node
            return yes == null && no == null;
      }
      
      public String toString() { // stores data as a string
            return data;
      }
} // end QuestionNode class