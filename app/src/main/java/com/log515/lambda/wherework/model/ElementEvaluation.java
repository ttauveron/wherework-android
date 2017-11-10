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

import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class ElementEvaluation extends AttributeContainer implements KvmSerializable
{

    
    public String coursGroupe;
    
    public String nom;
    
    public String equipe;
    
    public String dateCible;
    
    public String note;
    
    public String corrigeSur;
    
    public String ponderation;
    
    public String moyenne;
    
    public String ecartType;
    
    public String mediane;
    
    public String rangCentile;
    
    public String publie;
    
    public String messageDuProf;
    
    public String ignoreDuCalcul;

    public ElementEvaluation()
    {
    }

    public ElementEvaluation(java.lang.Object paramObj, ExtendedSoapSerializationEnvelope __envelope)
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
                if (info.name.equals("nom"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.nom = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.nom = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("equipe"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.equipe = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.equipe = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("dateCible"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.dateCible = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.dateCible = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("note"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.note = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.note = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("corrigeSur"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.corrigeSur = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.corrigeSur = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("ponderation"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.ponderation = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.ponderation = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("moyenne"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.moyenne = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.moyenne = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("ecartType"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.ecartType = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.ecartType = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("mediane"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.mediane = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.mediane = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("rangCentile"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.rangCentile = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.rangCentile = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("publie"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.publie = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.publie = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("messageDuProf"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.messageDuProf = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.messageDuProf = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("ignoreDuCalcul"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.ignoreDuCalcul = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.ignoreDuCalcul = (String)obj;
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
            return this.coursGroupe!=null?this.coursGroupe:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.nom!=null?this.nom:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.equipe!=null?this.equipe:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.dateCible!=null?this.dateCible:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.note!=null?this.note:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.corrigeSur!=null?this.corrigeSur:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.ponderation!=null?this.ponderation:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.moyenne!=null?this.moyenne:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==8)
        {
            return this.ecartType!=null?this.ecartType:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==9)
        {
            return this.mediane!=null?this.mediane:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==10)
        {
            return this.rangCentile!=null?this.rangCentile:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==11)
        {
            return this.publie!=null?this.publie:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==12)
        {
            return this.messageDuProf!=null?this.messageDuProf:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==13)
        {
            return this.ignoreDuCalcul!=null?this.ignoreDuCalcul:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 14;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "coursGroupe";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "nom";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "equipe";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "dateCible";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "note";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "corrigeSur";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ponderation";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "moyenne";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==8)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ecartType";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==9)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "mediane";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==10)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "rangCentile";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==11)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "publie";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==12)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "messageDuProf";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==13)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ignoreDuCalcul";
            info.namespace= "http://etsmtl.ca/";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

}
