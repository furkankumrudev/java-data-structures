package Lecture.Stack;

class StackA<T>{
    T[] data;
    int length;
    int top;
    StackA(T[] a){
        data = a;
        length = a.length;
        top = -1;
    }

    void push(T a){
        if(top < length-1){
            top++;
            data[top] = a;
        }else
            System.out.println("Stack Overflow");
    }

    T pop(){
        T a = null;
        if(top == -1){
            System.out.println("Stack Underflow");
            return null;
        }else{
            a = data[top];
            top--;
            return a;
        }
    }

    boolean isEmpty(){
        return top == -1 ? true : false;
    }

    void printStack(){
        if(top == -1){
            System .out.println("Stack Empty");
        }else{
            for(int i = top; i >= 0 ; i--){
                System.out.println(data[i] + " ");
            }
            System.out.println();     
        }
    }
    
}
class StackAImplementationDemo {
    public static void main(String[] args) {
        Integer a[] = new Integer[2];
        StackA<Integer> st = new StackA<Integer>(a);
    
        st.push(5);
        st.printStack();
        st.push(6);
        st.push(7);
        st.printStack();
        st.pop();
        st.printStack();
        st.pop();
        st.printStack();
        st.pop();
        System.out.println(" Is empty? " + st.isEmpty());
    }
}    