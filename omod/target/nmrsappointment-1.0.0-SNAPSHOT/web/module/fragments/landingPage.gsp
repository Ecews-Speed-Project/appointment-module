 <div id="newAppt" class="">
     <div class="new-appt-head px-2 py-3">
        <p>Create Appointment</p>
        <i class="fa-regular fa-circle-xmark" onClick="closeAppt()" id="closeAppt"></i>
     </div>
     <div class="pat-search-and-result">
         <div class="pat-search-form py-3 px-2">
            <input type="text" class="pat-search-input" oninput="findAppointmentPatient(this.value)" placeholder="Enter patient name or ART Number">
            <a class="pat-search-btn mx-auto my-auto">Search</a>
         </div>
         <div class="pat-search-result px-2 py-3" id="psr"></div>
     </div>

     <div class="pat-new-appt px-4 py-3 fw-bold hidden">
         <form class="form form-inline new-appt-form row">
            <div class="col-md-6">
                Appointment Date: <input type="date" id="apptDate" class="form-control">
            </div>
            <div class="col-md-6">
                Appointment Type:
                    <select  id="apptType" class="form-control form-select">
                        <option></option>
                        <option value="drug refill">Drug Refill</option>
                        <option value="eac">EAC</option>
                        <option value="viral load">Viral Load</option>
                    </select>
            </div>
            <div class="col-md-12 py-2">
                <label class="form-label">Notes / Comments </label>
                <textarea rows="5" id="apptNotes"></textarea>
            </div>
            <div class="col-md-6 py-3">
                 Visit Date: <input type="date" id="visitDate" class="form-control">
             </div>
             <div class="col-md-6 py-3">
                 Provider's Name:
                     <select  id="providerName" class="form-control form-select">
                         <option></option>

                     </select>
             </div>
            <div class="col-md-12 py-4">
                <a class="pat-appt-btn mx-auto my-auto">Save</a>
            </div>
         </form>
     </div>
 </div>
 <main class="mainDiv">
        <div class="patient-result">

            <div class="title">
                <h3>
                    Patient Appointment Module
                </h3>
            </div>
            <div class="appt-header mb-4">
                <div class="filter">
                    <form class="form searchDiv">
                        <div>
                            Start Date: <input type="date" id="startDate" class="form-control">
                        </div>
                        <div>
                            End Date: <input type="date" id="endDate" class="form-control">
                        </div>
                        <div>
                            <a class="btn btn-success btn-search py-2 px-4">
                                Search <i class="fa-solid fa-search"></i>
                            </a>
                        </div>
                    </form>
                </div>
                <div class="btn-Appt">
                    <a  onClick="openAppt()" id="openAppt" class="btn btn-success btn-appt py-2 px-4">
                        Create New Appointment <i class="fa-solid fa-hospital"></i>
                     </a>
                </div>
            </div>


            <div class="landingPage">
                <div>
                    <div class="icon-bg">
                        <i class="fa-solid fa-user-group icon"></i>
                    </div>

                    <h3>Today's Appointment</h3>
                    <h3 class="value" id="ta">300</h3>
                </div>
                <div>
                    <div class="icon-bg">
                        <i class="fa-solid fa-fingerprint icon"></i>
                    </div>
                    <h3>Recapture / Baseline</h3>
                    <div class="pbs-area">
                        <h3 class="value" id="pbs">200 / 290</h3>
                        <div class="percent bg-success-subtle" id="pbs_percent">
                            <h3>
                                <i class="fa-solid fa-arrow-up"></i> 70%
                            </h3>
                        </div>

                    </div>


                </div>
                <div>
                    <div class="icon-bg">
                        <i class="fa-solid fa-users-viewfinder icon"></i>
                    </div>
                    <h3>Patients without PBS</h3>
                    <div class="pbs-area">
                        <h3 class="value" id="no_pbs">90</h3>
                        <div class="percent percent-red bg-danger-subtle" id="no_pbs_percent">
                            <h3>
                                <i class="fa-solid fa-arrow-down"></i> 30%
                            </h3>
                       </div>

                    </div>
                </div>
            </div>
            <div class="appt-list">
            <table class="table  table-bordered table-hover">
              <caption class="caption-top captions">
                <span>Recent Appointments </i></span>
                <a href="#" class="float-end">
                    See all Appointments <i class="fa-solid fa-angle-right"></i>
                </a>
              </caption>

              <thead>
              <tr class="table-head">
                <th class="bg-success-subtle">Name
                    <span id="viewIcon" onClick="toggleNameShow();">
                        <i class="fa-solid fa-eye"></i>
                    </span>
                 </th>
                <th class="bg-success-subtle">Appointment Type</th>
                <th class="bg-success-subtle">Status</th>
                <th class="bg-success-subtle">Date</th>
                <th class="bg-success-subtle">Action</th>
              </tr>
              </thead>
              <tbody id="todayAppt" class="appt-landing"></tbody>
            </table>
        </div>
    </main>
