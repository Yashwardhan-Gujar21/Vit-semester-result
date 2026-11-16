package jar.model;

import jakarta.persistence.*;

@Entity
@Table(name = "semester_results")
public class StudentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prn;
    private String studentName;
    private String branch;

    // Subject 1: Design & Analysis of Algorithms (DAA)
    private double daaMse;
    private double daaEse;
    private double daaTotal;
    private String daaGrade;

    // Subject 2: Operating Systems (OS)
    private double osMse;
    private double osEse;
    private double osTotal;
    private String osGrade;

    // Subject 3: Computer Networks (CN)
    private double cnMse;
    private double cnEse;
    private double cnTotal;
    private String cnGrade;

    // Subject 4: Web Technology (WT)
    private double wtMse;
    private double wtEse;
    private double wtTotal;
    private String wtGrade;

    private double sgpa;
    private String overallStatus;

    @PrePersist
    @PreUpdate
    public void calculateResult() {
        this.daaTotal = round((daaMse * 0.30) + (daaEse * 0.70));
        this.osTotal  = round((osMse * 0.30) + (osEse * 0.70));
        this.cnTotal  = round((cnMse * 0.30) + (cnEse * 0.70));
        this.wtTotal  = round((wtMse * 0.30) + (wtEse * 0.70));

        this.daaGrade = computeGrade(this.daaTotal);
        this.osGrade  = computeGrade(this.osTotal);
        this.cnGrade  = computeGrade(this.cnTotal);
        this.wtGrade  = computeGrade(this.wtTotal);

        double totalPoints = getGradePoint(daaGrade) + getGradePoint(osGrade)
                           + getGradePoint(cnGrade) + getGradePoint(wtGrade);
        this.sgpa = round(totalPoints / 4.0);

        boolean anyFail = "F".equals(daaGrade) || "F".equals(osGrade)
                       || "F".equals(cnGrade) || "F".equals(wtGrade);
        this.overallStatus = anyFail ? "FAIL" : "PASS";
    }

    private String computeGrade(double marks) {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B+";
        if (marks >= 60) return "B";
        if (marks >= 50) return "C";
        if (marks >= 40) return "D";
        return "F";
    }

    private int getGradePoint(String grade) {
        return switch (grade) {
            case "A+" -> 10;
            case "A"  -> 9;
            case "B+" -> 8;
            case "B"  -> 7;
            case "C"  -> 6;
            case "D"  -> 5;
            default   -> 0;
        };
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPrn() { return prn; }
    public void setPrn(String prn) { this.prn = prn; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public double getDaaMse() { return daaMse; }
    public void setDaaMse(double daaMse) { this.daaMse = daaMse; }
    public double getDaaEse() { return daaEse; }
    public void setDaaEse(double daaEse) { this.daaEse = daaEse; }
    public double getDaaTotal() { return daaTotal; }
    public String getDaaGrade() { return daaGrade; }

    public double getOsMse() { return osMse; }
    public void setOsMse(double osMse) { this.osMse = osMse; }
    public double getOsEse() { return osEse; }
    public void setOsEse(double osEse) { this.osEse = osEse; }
    public double getOsTotal() { return osTotal; }
    public String getOsGrade() { return osGrade; }

    public double getCnMse() { return cnMse; }
    public void setCnMse(double cnMse) { this.cnMse = cnMse; }
    public double getCnEse() { return cnEse; }
    public void setCnEse(double cnEse) { this.cnEse = cnEse; }
    public double getCnTotal() { return cnTotal; }
    public String getCnGrade() { return cnGrade; }

    public double getWtMse() { return wtMse; }
    public void setWtMse(double wtMse) { this.wtMse = wtMse; }
    public double getWtEse() { return wtEse; }
    public void setWtEse(double wtEse) { this.wtEse = wtEse; }
    public double getWtTotal() { return wtTotal; }
    public String getWtGrade() { return wtGrade; }

    public double getSgpa() { return sgpa; }
    public String getOverallStatus() { return overallStatus; }
}