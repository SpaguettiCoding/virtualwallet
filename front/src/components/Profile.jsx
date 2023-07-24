import React from 'react'
import ProfileCardOne from './ProfileCardOne/ProfileCardOne'
import ProfileCardTwo from './ProfileCardTwo/ProfileCardTwo'


const Profile = () => {
  return (
    <div className='ProfileCards'>
        <ProfileCardOne></ProfileCardOne>
        <ProfileCardTwo></ProfileCardTwo>
    </div>
  )
}

export default Profile