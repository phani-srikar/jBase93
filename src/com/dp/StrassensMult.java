package com.dp;
import java.util.*;
import jBase93.common;

public class StrassensMult{
	
	public int[][] split(int[][] A, int r, int c){
		int n = A.length;
		int[][] R = new int[n/2][n/2];
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<n/2; j++)
				R[i][j] = A[r+i][c+j];
		}
		return R;
	}
	
	public int[][] add(int[][] A, int[][] B){
		int n = A.length;
		int R[][] = new int[n][n];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				R[i][j] = A[i][j] + B[i][j];
		return R;
	}
	
	public int[][] sub(int[][] A, int[][] B){
		int n = A.length;
		int R[][] = new int[n][n];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				R[i][j] = A[i][j] - B[i][j];
		return R;
	}
	
	public void reJoin(int[][] R, int[][] C, int r, int c) {
		int n = C.length;
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				R[r+i][c+j] = C[i][j];
	}
	
	public int[][] strassensMult(int[][] A, int[][] B){
		int n = A.length;
		int R[][] = new int[n][n];
		
		// base case
		if(n==1) {
			R[0][0] = A[0][0]*B[0][0];
			return R;
		}
		
		 /* 
        M1 = (A11 + A22)(B11 + B22)
        M2 = (A21 + A22) B11
        M3 = A11 (B12 - B22)
        M4 = A22 (B21 - B11)
        M5 = (A11 + A12) B22
        M6 = (A21 - A11) (B11 + B12)
        M7 = (A12 - A22) (B21 + B22)
      */
		
		int A11[][],A12[][],A21[][],A22[][];
		int B11[][],B12[][],B21[][],B22[][];
		
		A11 = split(A,0,0);
		A12 = split(A,0,n/2);
		A21 = split(A,n/2,0);
		A22 = split(A,n/2,n/2);
		
		B11 = split(B,0,0);
		B12 = split(B,0,n/2);
		B21 = split(B,n/2,0);
		B22 = split(B,n/2,n/2);
		
		int[][] M1 = strassensMult(add(A11,A22),add(B11,B22));
		int[][] M2 = strassensMult(add(A21,A22),B11);
		int[][] M3 = strassensMult(A11,sub(B12,B22));
		int[][] M4 = strassensMult(A22,sub(B21,B11));
		int[][] M5 = strassensMult(add(A11,A12),B22);
		int[][] M6 = strassensMult(sub(A21,A11),add(B11,B12));
		int[][] M7 = strassensMult(sub(A12,A22),add(B21,B22));
		
		/*
        C11 = M1 + M4 - M5 + M7
        C12 = M3 + M5
        C21 = M2 + M4
        C22 = M1 - M2 + M3 + M6
	   */
		int[][] C11 = add(add(M1,sub(M4,M5)),M7);
		int[][] C12 = add(M3,M5);
		int[][] C21 = add(M2,M4);
		int[][] C22 = add(add(sub(M1,M2),M3),M6);
		
		reJoin(R,C11,0,0);
		reJoin(R,C12,0,n/2);
		reJoin(R,C21,n/2,0);
		reJoin(R,C22,n/2,n/2);
		
		return R;
	}
	
	public int[][] naiveMult(int[][] A, int[][] B){
		int n = A.length;
		int R[][] = new int[n][n];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++) {
				R[i][j] = 0;
				for(int k=0; k<n; k++)
					R[i][j] = R[i][j] + (A[i][k]*B[k][j]);
			}
		return R;
	}
	
	public static void main(String[] args) {
		int[][] A = new int[4][4];
		int[][] B = new int[4][4];
		
		//generate random matrices
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				A[i][j] = (int) (Math.random()*10);
				B[i][j] = (int) (Math.random()*10);
			}
		
		System.out.println("Matrix A:");
		common.print(A);
		System.out.println("Matrix B:");
		common.print(B);
		
		StrassensMult sm = new StrassensMult();
		int[][] R = sm.naiveMult(A,B);
		System.out.println("result of naive method:");
		common.print(R);
		R = sm.strassensMult(A, B);
		System.out.println("result of Strassens method:");
		common.print(R);
	}
}










