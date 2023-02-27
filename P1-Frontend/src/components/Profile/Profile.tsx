import "./Profile.css"
export default function Profile() {
  return (
    <div>
      {/* <!-- component --> */}
      <section className="">
               
 
        
        <div className="profile-card">
          <h1 className="profile-name">first | last name</h1>
            <h2 className="profile-account">Account</h2>
            
              <div className="">
                <label className="profile-label">Username</label>
                <div className="profile-format">
                  <div className="profile-pic">
                    <svg
                      fill="none"
                      className="w-6 text-gray-400 mx-auto"
                      viewBox="0 0 24 24"
                      stroke="currentColor"
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"
                      /> 
                    </svg>
                  </div>
                  <input
                    type="text"
                    className="profile-input"
                    placeholder="username"
                  />
                </div>
                <label className="profile-label">Email</label>
                <div>
                  <div className="profile-format">
                    <div className="profile-pic">
                      <svg
                        fill="none"
                        className="w-6 text-gray-400 mx-auto"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                      >
                        <path
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"
                        />
                      </svg>
                    </div>
                    <input
                      type="email"
                      className="profile-input"
                      placeholder="email@example.com"
                    />
                  </div>
                </div>
              </div>
            
          

          <hr />
          <div className="">
            <h2 className="profile-account">Personal info</h2>
            <div className="">
              
              <label className="profile-label">First name</label>
              <div className="profile-format">
                <div className="profile-pic">
                  <svg
                    fill="none"
                    className="w-6 text-gray-400 mx-auto"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"
                    />
                  </svg>
                </div>
                <input
                  type="text"
                  className="profile-input"
                  placeholder="First Name"
                />
              </div>
              
              <div>
                <label className="profile-label">Last name</label>
                <div className="profile-format">
                  <div className="profile-pic">
                    <svg
                      fill="none"
                      className="w-6 text-gray-400 mx-auto"
                      viewBox="0 0 24 24"
                      stroke="currentColor"
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"
                      />
                    </svg>
                  </div>
                  <input
                    type="text"
                    className="profile-input"
                    placeholder="Last Name"
                  />
                </div>
              </div>
              <div>
                <label className="profile-label">Phone number</label>
                <div className="profile-format">
                  <div className="profile-pic">
                    <svg
                      fill="none"
                      className="w-6 text-gray-400 mx-auto"
                      viewBox="0 0 24 24"
                      stroke="currentColor"
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M12 18h.01M8 21h8a2 2 0 002-2V5a2 2 0 00-2-2H8a2 2 0 00-2 2v14a2 2 0 002 2z"
                      />
                    </svg>
                  </div>
                  <input
                    type="text"
                    className="profile-input"
                    placeholder="12341234"
                  />
                </div>
              </div>
            </div>
          </div>

          <hr />
          <div className="">
            <h2 className="profile-account">Change password</h2>

            <div className="">
              <div className="profile-format">
                <div className="profile-pic">
                  <svg
                    fill="none"
                    className="w-6 text-gray-400 mx-auto"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"
                    />
                  </svg>
                </div>
                <input
                  type="password"
                  className="profile-input"
                  placeholder="New"
                />
              </div>
            </div>

            <div className="">
              <button className="profile-button">
                <svg
                  fill="none"
                  className="w-4 text-white mr-2"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"
                  />
                </svg>
                Update
              </button>
            </div>
          </div>

          <hr />
          
          <button className="profile-delete">
            <svg
              fill="none"
              className="w-4 mr-2"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
              />
            </svg>
            Delete account
          </button>
          
        </div>
        
      </section>
    </div>
  );
}
