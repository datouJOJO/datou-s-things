homewor1.0 -input .\input\spline01_bezier.txt -gui -curve_tessellation 30
homewor1.0 -input .\input\spline02_bspline.txt -gui -curve_tessellation 30
homewor1.0 -input .\input\spline01_bezier.txt -output_bezier .\output\output01_bezier.txt
homewor1.0 -input .\input\spline01_bezier.txt -output_bspline .\output\output01_bspline.txt
homewor1.0 -input .\input\spline02_bspline.txt -output_bezier .\output\output02_bezier.txt
homewor1.0 -input .\input\spline02_bspline.txt -output_bspline .\output\output02_bspline.txt
homewor1.0 -input .\output\output01_bezier.txt -gui -curve_tessellation 30
homewor1.0 -input .\output\output01_bspline.txt -gui -curve_tessellation 30
homewor1.0 -input .\output\output02_bezier.txt -gui -curve_tessellation 30
homewor1.0 -input .\output\output02_bspline.txt -gui -curve_tessellation 30
homewor1.0 -input .\input\spline03_bezier.txt -gui -curve_tessellation 30
homewor1.0 -input .\input\spline04_bspline.txt -gui -curve_tessellation 30
homewor1.0 -input .\input\spline05_bspline_dups.txt -gui -curve_tessellation 30

homewor1.0 -input .\input\spline06_torus.txt -curve_tessellation 4 -gui
homewor1.0 -input .\input\spline06_torus.txt -curve_tessellation 4 -revolution_tessellation 10 -output .\output\torus_low.obj
homewor1.0 -input .\input\spline06_torus.txt -curve_tessellation 30 -revolution_tessellation 60 -output .\output\torus_high.obj

homewor1.0 -input .\input\spline07_vase.txt -curve_tessellation 4 -output_bspline output07_edit.txt -gui
homewor1.0 -input .\output\output07_edit.txt -curve_tessellation 4 -revolution_tessellation 10 -output .\output\vase_low.obj
homewor1.0 -input .\output\output07_edit.txt -curve_tessellation 10 -revolution_tessellation 60 -output .\output\vase_high.obj
