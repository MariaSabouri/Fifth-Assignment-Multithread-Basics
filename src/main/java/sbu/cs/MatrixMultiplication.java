package sbu.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixMultiplication {

    // You are allowed to change all code in the BlockMultiplier class
    static List<List<Integer>> tempMatrixProduct=new ArrayList<>();
    public static class BlockMultiplier implements Runnable
    {
        List<Integer> A=new ArrayList<>();
        List<Integer> B=new ArrayList<>();
        int x=0;
        int y=0;
        int q=0;
        public BlockMultiplier(List<Integer> A,List<Integer> B,int x,int y,int q) {
            // TODO
            this.A=A;
            this.B=B;
            this.x=x;
            this.y=y;
            this.q=q;

        }

        @Override
        public void run() {
            /*
            TODO
                Perform the calculation and store the final values in tempMatrixProduct
            */
            int result=0;
            for (int x=0;x<q;x++){
                result+=A.get(x)*B.get(x);
            }
            System.out.println("result"+x+y+" "+result);
            tempMatrixProduct.get(x).add(y,result);
            System.out.println("tempMatrixProduct "+tempMatrixProduct);

        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B) throws InterruptedException {
        /*
        TODO
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        ArrayList<Thread> threadArrayList=new ArrayList<>();
//        ExecutorService pool = Executors.newFixedThreadPool(4);

        int p=matrix_A.size();
        System.out.println("p "+p);
        int q=matrix_A.get(0).size();
        System.out.println("q "+q);
        int r=matrix_B.get(0).size();
        System.out.println("r "+r);

        for (int A=0;A<p;A++){
            for (int B=0;B<r;B++){
                List<Integer> C=new ArrayList<>();
                for (int x=0;x<q;x++){
                    C.add(matrix_B.get(x).get(B));
                }
                Thread thread=new Thread(new BlockMultiplier(matrix_A.get(A),C,A,B,q));
                threadArrayList.add(thread);
            }
            tempMatrixProduct.add(new ArrayList<>());

        }
        for (Thread thread:threadArrayList){
//            pool.execute(thread);
            thread.start();
            thread.join();
        }


        System.out.println("threads number "+threadArrayList.size());

        return tempMatrixProduct;
    }

    public static void main(String[] args) {
        // Test your code here
        List<List<Integer>> A=new ArrayList<>(List.of(List.of(1,2,3,4),List.of(1,1,1,1)));
//        System.out.println(A);
        List<List<Integer>> B=new ArrayList<>(List.of(List.of(1,2),List.of(1,1),List.of(3,3),List.of(0,5)));
//        System.out.println(B);
        try {
            System.out.println("ParallelizeMatMul "+ParallelizeMatMul(A,B));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
