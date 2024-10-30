// https://docs.cypress.io/api/table-of-contents

cy.login = () => {
  cy.request({
    method: 'POST',
    url: '/api/auth/login',
    body:{
      username: "user@usermail.com",
      password: "Wachtwoord123!P"
    }
  }).then((resp) => {
    window.localStorage.setItem('token', resp.body.token);
  });

};

describe('RegisterTest', () => {
  it('Registers a new user', () => {
    cy.visit('/')
    cy.contains('Login / Register').click()
    cy.contains('Create new account').click()
    const password = "Wachtwoord123!P"
    cy.intercept('POST', '/api/auth/register').as('registerUser')
    cy.get('input[placeholder="Username"]').type("user")
    cy.get('input[placeholder="Email"]').type("user@usermail.com")
    cy.get('input[placeholder="Password"]').type(password)
    cy.get('input[placeholder="Repeat password"]').type(password)
    cy.get('button.submitButton').click();
    cy.wait('@registerUser').then((interception) => {
      expect(interception.response.statusCode).to.equal(200);
    })



  })
})

describe('LoginTest', () => {
  it('should_login_user_with_correct_credentials', () => {
    cy.visit('/')
    cy.contains('Login / Register').click()
    cy.intercept('POST', '/api/auth/login').as('login')
    cy.get('input[placeholder="Username"]').type("user@usermail.com")
    cy.get('input[placeholder="Password"]').type("Wachtwoord123!P")
    cy.get('button.submitButton').click();
    cy.wait('@login').then((interception) => {
      expect(interception.response.statusCode).to.equal(200);
    })
  })
});


describe('PlayTest', () => {
  beforeEach(()=>{
    cy.visit('/')
    cy.contains('Login / Register').click()
    cy.intercept('POST', '/api/auth/login').as('login')
    cy.get('input[placeholder="Username"]').type("user@usermail.com")
    cy.get('input[placeholder="Password"]').type("Wachtwoord123!P")
    cy.get('button.submitButton').click();
    cy.wait('@login').then((interception) => {
      expect(interception.response.statusCode).to.equal(200);
    })

  })
  it('should_build_buildings_when_logged_in', () =>{
    cy.get(".clickableTile").eq(9).click()
    cy.get('[data-cy="House"]').click();
    cy.contains("Construction Times")

  })

});