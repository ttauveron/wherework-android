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

public class Cours extends AttributeContainer implements KvmSerializable
{

    
    public String sigle;
    
    public String groupe;
    
    public String session;
    
    public String programmeEtudes;
    
    public String cote;
    
    public Integer nbCredits=0;
    
    public String titreCours;

    public Cours()
    {
    }

    public Cours(java.lang.Object paramObj, ExtendedSoapSerializationEnvelope __envelope)
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
                if (info.name.equals("sigle"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.sigle = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.sigle = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("groupe"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.groupe = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.groupe = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("session"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.session = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.session = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("programmeEtudes"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.programmeEtudes = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.programmeEtudes = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("cote"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.cote = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.cote = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("nbCredits"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.nbCredits = Integer.parseInt(j.toString());
                            }
                        }
                        else if (obj instanceof Integer){
                            this.nbCredits = (Integer)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("titreCours"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.titreCours = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.titreCours = (String)obj;
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
            return this.sigle!=null?this.sigle:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.groupe!=null?this.groupe:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.session!=null?this.session:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.programmeEtudes!=null?this.programmeEtudes:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.cote!=null?this.cote:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return nbCredits;
        }
        if(propertyIndex==6)
        {
            return this.titreCours!=null?this.titreCours:SoapPrimitive.NullSkip;
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
            info.name = "sigle";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "groupe";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "session";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "programmeEtudes";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "cote";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "nbCredits";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "titreCours";
            info.namespace= "http://etsmtl.ca/";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

}