const Header = () => {
  return(
    <nav class="navbar fixed-top navbar-expand-lg navbar-light" style={ {backgroundColor: "#03acac"} }>
      <a class="navbar-brand" style={ {paddingLeft: 20} }>Freelance App</a>
      <div class="w-100">
        <ul class="nav navbar-nav flex-row justify-content-md-end justify-content-end flex-nowrap">
          <li class="nav-item"><a class="btn btn-bd-download nav-link" href="/buyerbids">My Bids</a></li>
          <li class="nav-item"><a class="btn btn-bd-download nav-link" href="/sellerprojects">My Projects</a></li>
          <li class="nav-item"><a class="btn btn-bd-download nav-link" href="/sellerprojects">Create New Project</a></li>
        </ul>
      </div>
    </nav>
  )
}

export default Header