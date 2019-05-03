import jomp.runtime.*;

public class PiJOMP {


     static final long N = 2000000000;

public static void main(String[] args) {

     double pi = 0.0f;
     long i;

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.pi = pi;
  __omp_Object0.args = args;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  pi = __omp_Object0.pi;
  args = __omp_Object0.args;
}
// OMP PARALLEL BLOCK ENDS
 
 
     System.out.println(String.format("Pi = %11.10f\n",pi/N));
}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  double pi;
  String [ ] args;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  long i;
  // reduction variables, init to default
    // OMP USER CODE BEGINS

          { // OMP FOR BLOCK BEGINS
          // copy of firstprivate variables, initialized
          // copy of lastprivate variables
          // variables to hold result of reduction
          double _cp_pi;
          boolean amLast=false;
          {
            // firstprivate variables + init
            // [last]private variables
            // reduction variables + init to default
            double  pi = 0;
            // -------------------------------------
            jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
            jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
            __omp_WholeData2.start = (long)(0);
            __omp_WholeData2.stop = (long)( N);
            __omp_WholeData2.step = (long)(1);
            jomp.runtime.OMP.setChunkStatic(__omp_WholeData2);
            while(!__omp_ChunkData1.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData2, __omp_ChunkData1)) {
            for(;;) {
              if(__omp_WholeData2.step > 0) {
                 if(__omp_ChunkData1.stop > __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                 if(__omp_ChunkData1.start >= __omp_WholeData2.stop) break;
              } else {
                 if(__omp_ChunkData1.stop < __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                 if(__omp_ChunkData1.start > __omp_WholeData2.stop) break;
              }
              for( i = (long)__omp_ChunkData1.start; i < __omp_ChunkData1.stop; i += __omp_ChunkData1.step) {
                // OMP USER CODE BEGINS
 {
           double t = (double) ((i+0.5)/N);
           pi += 4.0/(1.0+t*t);
       }
                // OMP USER CODE ENDS
                if (i == (__omp_WholeData2.stop-1)) amLast = true;
              } // of for 
              if(__omp_ChunkData1.startStep == 0)
                break;
              __omp_ChunkData1.start += __omp_ChunkData1.startStep;
              __omp_ChunkData1.stop += __omp_ChunkData1.startStep;
            } // of for(;;)
            } // of while
            // call reducer
            _cp_pi = (double) jomp.runtime.OMP.doPlusReduce(__omp_me, pi);
            jomp.runtime.OMP.doBarrier(__omp_me);
            // copy lastprivate variables out
            if (amLast) {
            }
          }
          // set global from lastprivate variables
          if (amLast) {
          }
          // set global from reduction variables
          if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
            pi+= _cp_pi;
          }
          } // OMP FOR BLOCK ENDS

    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

