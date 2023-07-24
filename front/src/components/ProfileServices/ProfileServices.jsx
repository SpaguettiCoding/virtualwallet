import React, { useState } from 'react'
import s from "./ProfileServices.module.css"
// import { IoAnalytics } from 'react-icons/io';
// import {AiFillCreditCard} from "react-icons/ai"
// import {MdOutlineDesignServices} from "react-icons/md"
import Card from '../Card/Card';
import Loan from '../Loan/Loan';
import Analysis from '../Analysis/Analysis';
import Payments from '../Payments/Payments';


const ProfileServices = () => {

    const [actualService, setActualService] = useState('Card')

    let services = ["Card", "Loans", "Analysis", "Payments" ]

    const handleActualService = (service) => {
        setActualService(service)
    }

  return (
    <div className={s.ServicesWrapper}>
        <div className={s.ServicesList}>
            {services.map(service => (
                <span onClick={() => handleActualService(service)}>{service}</span>
            ))}
        </div>
    
        <div className={s.servicesBody}>
            {actualService == "Card"   && <Card/>}
            {actualService == "Loans" && <Loan/>}
            {actualService == "Analysis" && <Analysis/>}
            {actualService == "Payments" && <Payments/>}
        </div>
       
    </div>
  )
}

export default ProfileServices