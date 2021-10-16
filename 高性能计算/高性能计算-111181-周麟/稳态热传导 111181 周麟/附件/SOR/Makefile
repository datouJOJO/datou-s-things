
FC = gfortran
FFLAGS = -fbounds-check -fopenmp
LFLAGS = $(FFLAGS)

.PHONY: plots, clean, clobber

%.o : %.f90
	$(FC) $(FFLAGS) -c $<

SOR.exe:  SOR_main_omp.o SOR_sub_omp.o
	$(FC) $(LFLAGS) SOR_main_omp.o SOR_sub_omp.o -o $@

solution.txt: SOR.exe
	@echo 
	@echo Running code...
	./SOR.exe

plots: solution.txt
	@echo 
	@echo Plotting results...
	python3 plot_solution.py

clean:
	rm -f *.o *.exe

clobber: clean
	rm -f solution.txt *.png

