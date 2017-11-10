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

public class Enseignant extends Personne implements KvmSerializable
{

    
    public String localBureau;
    
    public String telephone;
    
    public String enseignantPrincipal;

    public Enseignant()
    {
    }

    public Enseignant(java.lang.Object paramObj, ExtendedSoapSerializationEnvelope __envelope)
    {
	    super(paramObj, __envelope);
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
                if (info.name.equals("localBureau"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.localBureau = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.localBureau = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("telephone"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.telephone = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.telephone = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("enseignantPrincipal"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.enseignantPrincipal = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.enseignantPrincipal = (String)obj;
                        }
                    }
                    continue;
                }

            }

        }



    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        int count = super.getPropertyCount();
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==count+0)
        {
            return this.localBureau!=null?this.localBureau:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==count+1)
        {
            return this.telephone!=null?this.telephone:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==count+2)
        {
            return this.enseignantPrincipal!=null?this.enseignantPrincipal:SoapPrimitive.NullSkip;
        }
        return super.getProperty(propertyIndex);
    }


    @Override
    public int getPropertyCount() {
        return super.getPropertyCount()+3;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        int count = super.getPropertyCount();
        if(propertyIndex==count+0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "localBureau";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==count+1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "telephone";
            info.namespace= "http://etsmtl.ca/";
        }
        if(propertyIndex==count+2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "enseignantPrincipal";
            info.namespace= "http://etsmtl.ca/";
        }
        super.getPropertyInfo(propertyIndex,arg1,info);
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

}