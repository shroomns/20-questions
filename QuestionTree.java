// Joy Schwarting
// 20 Questions
//
// This is file 2/2 I am turning in (the other is QuestionNode.java).
// Obviously you need the .txt files for the questions and the two 
// other .java files (QuestionMain.java and UserInterface.java) that
// you provided for this assignment, all within the same folder.
//
// QuestionTree.java: this class implements a binary tree system based
// on the users answers (yes or no). If the answer is not already in the
// tree and saved .txt files, the tree will be updated following
// user input.

import java.io.*;
import java.util.*;

public class QuestionTree {
      
      private QuestionNode root;
      // "root" of tree, stores questions and answers
           
      private UserInterface uInter;  
        
      private int games; 
          
      private int wins;
      
      // constructor that initializes question tree
      public QuestionTree(UserInterface ui) {
            // contains one "leaf" node with answer: computer
            root = new QuestionNode("computer");
            uInter = ui;
            games = 0; // starting off with zero games played
            wins = 0; // starting off with zero games won
      }
      
      // plays one complete game until arriving at an answer
      public void play() {
            root = playedNode(root);
            games++;
            // adds to games played
      } // end play() method
      
      private QuestionNode playedNode(QuestionNode node) {
      // will pass through QuestionNode
            if (node.isAnswer()) {
                  uInter.print("Is your object a " + node + "?");
                  if (uInter.nextBoolean()) {
                        // if guess is same as user's answer: 
                        uInter.println("Ha! I win!");
                        wins++; // add one to win count
                  } else {
                        node = learnedNode(node);
                  }
             } else {
                  uInter.print(node.toString());
                  if (uInter.nextBoolean()) {
                        node.yes = playedNode(node.yes);
                  } else {
                        node.no = playedNode(node.no);
                  }
             }
             return node;
      }
       
      // this section allows updated questions/answers, "teaching" the computer
      private QuestionNode learnedNode(QuestionNode node) {
            uInter.print("I lost. What is your object? ");
            QuestionNode newNode = new QuestionNode (uInter.nextLine());
            
            uInter.print("Type a yes or no question to distinguish " +
                  "your object from " + node + ":");
            String query = uInter.nextLine();
            
            uInter.print("& what is the correct answer for your object?");
            return uInter.nextBoolean() ?
                  new QuestionNode (newNode, node, query):
                  new QuestionNode (node, newNode, query);
            }
            
            public void save (PrintStream output) {
            // store current tree state to output file
                  save(output, root);
            }
            
            private void save(PrintStream out, QuestionNode node) {
            // saves to tree state
                  if (node.isAnswer()){
                        out.print("A:" + node);
                  } else {
                        out.println("Q:" + node);
                        save(out, node.yes);
                        out.println();
                        save(out, node.no);
                  }
            }
            
            public void load (Scanner input) {
            // replace current tree by reading another tree from file
                  root = loadedNode (input);
            }
            
            private QuestionNode loadedNode (Scanner input) {
            // loads question tree recursively from scanner input
                  QuestionNode node = null;
                  
                  if (input.hasNext()){
                        String[] data = input.nextLine().split(":",2);
                        if (data[0].equals("A")){
                              node = new QuestionNode(data[1]);
                        } else {
                              node = new QuestionNode (loadedNode(input),loadedNode(input),data[1]);
                        }
                  }
                  return node;
            }
            
            public int totalGames() {
            // returns total number of games played
                  return games;
            }
            
            public int gamesWon() {
            // returns total numbers of games won
                  return wins;
            }
} // end QuestionTree class