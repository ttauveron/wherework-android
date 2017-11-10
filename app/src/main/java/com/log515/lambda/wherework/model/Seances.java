package com.log515.lambda.wherework.model;
//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.5.8.1
//
// Created by Quasar Development at 06/09/2016
//
//---------------------------------------------------


import com.log515.lambda.wherework.soap.ExtendedSoapSerializationEnvelope;
import com.log515.lambda.wherework.soap.Helper;

import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class Seances extends AttributeContainer implements KvmSerializable
{
    
    public java.util.Date dateDebut;
    
    public java.util.Date dateFin;
    
    public String coursGroupe;
    
    public String nomActivite;
    
    public String local;
    
    public String descriptionActivite;
    
    public String libelleCours;

    public Seances()
    {
    }

    public String getId() {
        return coursGroupe +
                dateDebut +
                dateFin +
                local;
    }

    public Seances(java.lang.Object paramObj, ExtendedSoapSerializationEnvelope __envelope)
    {
	    
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        if(inObj instanceof SoapObject)
        {
            SoapObject soapObject=(SoapObject)inObj;
            int size = soapObject.getPropertyCount();
            for (int i0=0;i0< size;i0++)
            {
                //if you have compilation error here, please use a ksoap2.jar and ExKsoap2.jar from libs folder (in the generated zip file)
                PropertyInfo info=soapObject.getPropertyInfo(i0);
                java.lang.Object obj = info.getValue(); 
                if (info.name.equals("dateDebut"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.dateDebut = Helper.ConvertFromWebService(j.toString());
                            }
                        }
                        else if (obj instanceof java.util.Date){
                            this.dateDebut = (java.util.Date)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("dateFin"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.dateFin = Helper.ConvertFromWebService(j.toString());
                            }
                        }
                        else if (obj instanceof java.util.Date){
                            this.dateFin = (java.util.Date)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("coursGroupe"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.coursGroupe = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.coursGroupe = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("nomActivite"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.nomActivite = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.nomActivite = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("local"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.local = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.local = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("descriptionActivite"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.descriptionActivite = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.descriptionActivite = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("libelleCours"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.libelleCours = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.libelleCours = (String)obj;
                        }
                    }
                    continue;
                }

            }

        }



    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.dateDebut!=null? Helper.getDateTimeFormat().format(this.dateDebut):SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.dateFin!=null? Helper.getDateTimeFormat().format(this.dateFin):SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.coursGroupe!=null?this.coursGroupe:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.nomActivite!=null?this.nomActivite:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.local!=null?this.local:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.descriptionActivite!=null?this.descriptionActivite:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.libelleCours!=null?this.libelleCours:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 7;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "dateDebut";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "dateFin";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "coursGroupe";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "nomActivite";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "local";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "descriptionActivite";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "libelleCours";
            info.namespace= "http://etsmtl.ca/";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

}
