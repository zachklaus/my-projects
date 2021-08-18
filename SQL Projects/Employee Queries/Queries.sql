SELECT
  e.first_name, e.last_name, s.salary
FROM
  employees e, salaries s
WHERE
  e.emp_no = s.emp_no AND s.salary > 90000 AND s.to_date = '9999-01-01';


SELECT
  e.first_name, e.last_name, d.dept_name
FROM
  employees e
  JOIN current_dept_emp cd ON e.emp_no = cd.emp_no
  JOIN departments d ON d.dept_no = cd.dept_no
WHERE
  cd.to_date = '9999-01-01'
  AND d.dept_no = 'd008'
  OR d.dept_no = 'd009';


SELECT
  e.first_name, e.last_name, t.title
FROM
  employees e
  JOIN titles t ON e.emp_no = t.emp_no
WHERE
  t.to_date = '9999-01-01'
  AND t.title = 'Technique Leader'
  AND e.gender = 'F';


SELECT
  e.first_name, e.last_name, s.salary
FROM
  employees e
  JOIN salaries s ON s.emp_no = e.emp_no
  JOIN titles t ON t.emp_no = e.emp_no
WHERE
  s.to_date = '9999-01-01'
  AND t.to_date = '9999-01-01'
  AND t.title <> 'Senior Engineer'
ORDER BY
  s.salary;


SELECT
  e.first_name, e.last_name, e.birth_date
FROM
  employees e
ORDER BY
  e.birth_date DESC;


SELECT
  e.first_name, e.last_name
FROM
  employees e
  JOIN dept_manager dm ON dm.emp_no = e.emp_no
WHERE
  dm.to_date = '9999-01-01';


SELECT
  e.first_name, e.last_name, d.dept_name
FROM
  employees e
  JOIN salaries s ON s.emp_no = e.emp_no
  JOIN current_dept_emp cd ON cd.emp_no = e.emp_no
  JOIN departments d ON d.dept_no = cd.dept_no
WHERE
  s.to_date = '9999-01-01'
  AND cd.to_date = '9999-01-01'
  AND s.salary =
    (SELECT MAX(salary) FROM salaries AS max_salary);
