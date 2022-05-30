/*
    https://leetcode.com/problems/valid-parentheses
*/

/*
    Approach
        - If the character is any of the open brackets, push it to a stack
        - Else, if the closing bracket matches that of opening bracket on
          top of stack, continue by popping the stack, else return false
        - The logic here is, If the parantheses is balanced, popped char must
          be the opening bracket of the current char.
          For ex:
                [{}]
                Here stack would be 
                        |  {  |
                        |  [  |
                So, curr char, i.e } is the closing bracket of stack top
*/

/*
    Space complexity
        - O(n)
    Time complexity
        - O(n)
        
*/

class Solution {
public:
    bool isValid(string s) {
        stack<char> st;
        for(int i=0; i<s.length(); i++) {
            //push to the stack if the char is opening bracket
            if(s[i] == '{' || s[i] == '(' || s[i] == '[') {
                st.push(s[i]);
            } else {
                /* for all the below conditions, return false.
                   stack empty stands for input starts with closing
                   bracket */
                
                if(st.empty()                       || 
                   st.top() == '(' && s[i] != ')'   ||
                   st.top() == '{' && s[i] != '}'   ||
                   st.top() == '[' && s[i] != ']') {
                    return false;
                }
                
                st.pop();
            }
        }
    
        //if stack is not empty, it means there are few closures remaining
        return st.empty();
    }
};