package com.musevisions.android.SudokuSolver;

import com.musevisions.android.SudokuSolver.SudokuCore.SolverListener;
import com.musevisions.android.SudokuSolver.SudokuCore.SolverMethod;

import android.os.AsyncTask;


public class SudokuSolverTask extends AsyncTask<Void, int[], Void> implements SolverListener {
    int mPuzzle[];
    int mSolution[];
    SolverListener mListener;
    GridView mGridView;
    SolverMethod mMethod;

    public SudokuSolverTask(int puzzle[],
    		SolverListener listener, GridView updateView, SolverMethod method) {
        mPuzzle = puzzle;
        mListener = listener;
        mGridView = updateView;
        mMethod = method;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        //mSolution = SudokuCore.solve(mPuzzle, this);
    	switch(mMethod) {
    	case SOLVER_BRUTE_FORCE:
            mSolution = SudokuCore.solveMethodBruteForce(mPuzzle, mListener);
            break;
    	case SOLVER_OPTIMISED:
            mSolution = SudokuCore.solveMethodOptimised(mPuzzle);
            break;            
    	}
        return null;
    }
    
    @SuppressWarnings({"UnusedDeclaration"})
    protected void onProgressUpdate(int[]... values) {
    	mGridView.setGameInput(values[0]);
    }

    /** Listener */
    @Override
    protected void onPostExecute(Void result) {
        mListener.onSolverEvent(mSolution);
    }

    /** Updater */
	@Override
	public void onSolverEvent(int[] result) {
		publishProgress(result);
	}


}