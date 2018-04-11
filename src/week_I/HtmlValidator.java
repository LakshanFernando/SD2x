package week_I;

import week_I.HtmlTag;

import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

@SuppressWarnings("ALL")
public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
	    // if tag is self closing, don't add to the stack
        //if a tag is a closing tag and the last in the stack is not an opening tag that marches the tag then return
        // the stack
        Stack<HtmlTag> stack = new Stack<>();
        while (!tags.isEmpty()) {
            HtmlTag tag = tags.peek();
            if (tag != null) {
                if (tag.isOpenTag()) {
                    stack.push(tags.remove());
                }
                else if (tag.isSelfClosing()) tags.remove();
                else if (!stack.isEmpty() && tag.matches(stack.peek())) {
                    stack.pop();
                    tags.remove();
                }
                else if(stack.isEmpty()) return null;
                else return stack;
            }
        }
        return stack;
	}


}

