
FC = gfortran
FFLAGS = -fbounds-check -fopenmp
LFLAGS = $(FFLAGS)

.PHONY: plots, clean, clobber

%.o : %.f90
	$(FC) $(FFLAGS) -c $<

Gauss.exe:  GaussSeidel_main_omp.o GaussSeidel_sub_omp.o
	$(FC) $(LFLAGS) GaussSeidel_main_omp.o GaussSeidel_sub_omp.o -o $@

solution.txt: Gauss.exe
	@echo 
	@echo Running code...
	./Gauss.exe

plots: solution.txt
	@echo 
	@echo Plotting results...
	python3 plot_solution.py

clean:
	rm -f *.o *.exe

clobber: clean
	rm -f solution.txt *.png

