import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String Name;
    private String Surname;
    private String Patronymic;
    private LocalDate BirthDate;
    private String Sex;
    public Person(String enter) throws InvalidParameterException{
        String[] info = enter.split(" ");
        if(info.length!=4){
            throw new InvalidParameterException("Неверный формат ввода информации.");
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<info[i].length();j++){
                if (!(Character.UnicodeBlock.of(info[i].charAt(j)).equals(Character.UnicodeBlock.CYRILLIC))) {
                    throw new InvalidParameterException("Недопустимые в ФИО символы.");
                }
            }
        }
        Surname = Character.toUpperCase(info[0].charAt(0))+(info[0].substring(1)).toLowerCase();
        Name = Character.toUpperCase(info[1].charAt(0))+(info[1].substring(1)).toLowerCase();
        Patronymic = Character.toUpperCase(info[2].charAt(0))+(info[2].substring(1)).toLowerCase();
        String[] dateinfo = info[3].split("\\.");
        if(dateinfo.length!=3){
            throw new InvalidParameterException("Неверный формат ввода даты рождения.");
        }
        try{
            BirthDate = LocalDate.of(Integer.parseInt(dateinfo[2]),Integer.parseInt(dateinfo[1]),Integer.parseInt(dateinfo[0]));
            if(Period.between(BirthDate, LocalDate.now()).getYears()<0){
                throw new InvalidParameterException("Человек из будущего.");
            }
        }
        catch(NumberFormatException| DateTimeException ex){
            throw new InvalidParameterException("Несуществующая дата рождения.");
        }
        if(Patronymic.charAt(Patronymic.length()-1)=='ч' || Patronymic.charAt(Patronymic.length()-1)=='Ч'){
            Sex="М";
        }
        else if(Patronymic.charAt(Patronymic.length()-1)=='а' || Patronymic.charAt(Patronymic.length()-1)=='А'){
            Sex="Ж";
        }
        else{
            Sex="Undefined";
        }
    }
    public String toString(){
        String age;
        int period = Period.between(BirthDate, LocalDate.now()).getYears();
        if((period%100)/10==1){
            age="лет";
        }
        else if((period%100)%10==1){
            age="год";
        }
        else if((period%100)%10>=5 || (period%100)%10==0){
            age="лет";
        }
        else{
            age="года";
        }
        return new String(Surname+" "+Name.charAt(0)+"."+Patronymic.charAt(0)+". "+Sex+" "+period+" "+age);
    }
}
