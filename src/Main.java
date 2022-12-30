import java.util.Objects;
import java.util.Random;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String prod = scnr.nextLine();
        int type = Integer.parseInt(scnr.nextLine());
        int gb = Integer.parseInt(scnr.nextLine());
        RAM ram = new RAM(prod, type, gb);
        System.out.println(ram.toString());

        prod = "Seagate";
        boolean ssd = true;
        gb = 250;
        winchester disk = new winchester(prod, ssd, gb);
        System.out.println(disk.toString());

        prod = "AMD";
        String model = "Ryzen 5 4600x";
        int cores = 6;
        boolean hyper_threading = true;
        CPU cpu = new CPU(prod, model, cores, hyper_threading);
        System.out.println(cpu.toString());

        computer pc = new computer(cpu, ram, disk);
        System.out.println(pc.toString());
        pc.turn_off();
        pc.turn_on();
        pc.disk_size();
        pc.viruses_check();
        prod = scnr.nextLine();
        type = Integer.parseInt(scnr.nextLine());
        gb = Integer.parseInt(scnr.nextLine());
        RAM ram2 = new RAM(prod, type, gb);

        computer pc2 = new computer(cpu, ram2, disk);
        System.out.println(pc.equals(pc2));
    }
}

class RAM {
    String producer;
    int ddr_type;
    int Gb;

    public RAM(String prod, int type, int gb) {
        producer = prod;
        ddr_type = type;
        Gb = gb;
    }

    @Override
    public String toString() {
        String info = producer + " DDR";
        switch (ddr_type) {
            case 3 -> info += "3 ";
            case 4 -> info += "4 ";
            case 5 -> info += "5 ";
        }
        info += String.valueOf(Gb) + "Gb";
        return "RAM: " + info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RAM ram)) return false;
        return ddr_type == ram.ddr_type && Gb == ram.Gb && producer.equals(ram.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producer, ddr_type, Gb);
    }
}

class winchester {
    String producer;
    boolean SSD;
    int Gb;

    public winchester(String prod, boolean ssd, int gb) {
        producer = prod;
        SSD = ssd;
        Gb = gb;
    }

    @Override
    public String toString() {
        String info = producer + " ";
        if (SSD)
            info += "SSD ";
        else info += "HDD ";
        info += String.valueOf(Gb) + "Gb";
        return "Winchester: " + info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof winchester that)) return false;
        return SSD == that.SSD && Gb == that.Gb && producer.equals(that.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producer, SSD, Gb);
    }
}

class CPU {
    String producer;
    String model;
    int cores;
    boolean hyper_threading;

    public CPU(String prod, String mod, int cor, boolean ht) {
        producer = prod;
        model = mod;
        cores = cor;
        hyper_threading = ht;
    }

    @Override
    public String toString() {
        String info = producer + " " + model + " "+ String.valueOf(cores) + " cores / ";
        if (hyper_threading)
            info += String.valueOf(cores * 2) + " threads";
        else info += String.valueOf(cores) + " threads";
        return "CPU: " + info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CPU cpu)) return false;
        return cores == cpu.cores && hyper_threading == cpu.hyper_threading && producer.equals(cpu.producer) && model.equals(cpu.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producer, model, cores, hyper_threading);
    }
}

class computer {
    CPU cpu;
    RAM ram;
    winchester disk;

    boolean turned_on = false;

    public computer(CPU cp, RAM rm, winchester d) {
        cpu = cp;
        ram = rm;
        disk = d;
    }

    @Override
    public String toString() {
        return "Computer info: " + cpu.toString() + ",\t" + ram.toString() + ",\t" + disk.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof computer computer)) return false;
        return cpu.equals(computer.cpu) && ram.equals(computer.ram) && disk.equals(computer.disk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpu, ram, disk, turned_on);
    }

    public void turn_on(){
        if (turned_on)
            System.out.println("Already turned on");
        else turned_on = true;
    }

    public void turn_off(){
        if (!turned_on)
            System.out.println("Already turned off");
        else turned_on = false;
    }

    public void disk_size(){
        if(!turned_on)
            System.out.println("Impossible while turned off");
        else System.out.println("Disk size: " + disk.Gb + "Gb");
    }

    public void viruses_check(){
        if(!turned_on)
            System.out.println("Impossible while turned off");
        else{
            int a = new Random().nextInt(3);
            if (a == 0)
                System.out.println("Some viruses were present. Now deleted.");
            if (a == 1 || a == 2)
                System.out.println("System is clean.");
        }
    }
}