package HW_Exceptions.HW_Exceptions3.DataProcessing;

import java.time.LocalDate;

public class UserData {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private Long number;
    private String sex;

    public UserData(String name, String surname, String patronymic, LocalDate birthday, Long number, String sex) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.number = number;
        this.sex = sex;
    }

    public UserData() {
        new UserData(null,null,null,null,null,null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + patronymic + " " + birthday.getDayOfMonth() + "." + birthday.getMonthValue()+"."+ birthday.getYear() +" " + number + " " + sex;
    }
}
