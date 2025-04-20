import React, { useState } from 'react';
import {
  Box,
  Button,
  Container,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Paper,
  Typography,
  Grid,
  Switch,
  FormControlLabel
} from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';
import LockIcon from '@mui/icons-material/Lock';
import PersonalInfoContent from './PersonalInfoContent';
import SecurityContent from './SecurityContent';

function UserProfile() {
  // State to track which section is currently selected
  const [activeSection, setActiveSection] = useState('personalInfo');

  const handleSectionChange = (section) => {
    setActiveSection(section);
  };
  // Render the appropriate content based on the activeSection
  const renderContent = () => {
    switch (activeSection) {
      case 'personalInfo':
        return <PersonalInfoContent />;
      case 'security':
        return <SecurityContent />;
      default:
        return <PersonalInfoContent />;
    }
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      {/* Breadcrumb */}
      <Container maxWidth="lg" sx={{ mt: 2, mb: 2 }}>
        <Typography variant="body2" component="div">
          Tài khoản &gt; {activeSection === 'personalInfo' ? 'Thông tin cá nhân' : activeSection === 'security' ? 'Quản lý tài khoản' : ''}
        </Typography>
      </Container>

      {/* Main content */}
      <Container maxWidth="lg">
        <Grid container spacing={3}>
          {/* Left sidebar */}
          <Grid item xs={12} md={3}>
            <Paper>
              <List component="nav">
                <ListItem
                  sx={{ cursor: 'pointer' }}
                  selected={activeSection === 'personalInfo'}
                  onClick={() => handleSectionChange('personalInfo')}
                >
                  <ListItemIcon>
                    <PersonIcon />
                  </ListItemIcon>
                  <ListItemText primary="Thông tin cá nhân" />
                </ListItem>
                <ListItem
                  sx={{ cursor: 'pointer' }}
                  selected={activeSection === 'security'}
                  onClick={() => handleSectionChange('security')}
                >
                  <ListItemIcon>
                    <LockIcon />
                  </ListItemIcon>
                  <ListItemText primary="Quản lý tài khoản" />
                </ListItem>
              </List>
            </Paper>
          </Grid>

          {/* Right content - dynamic based on selected section */}
          <Grid item xs={12} md={9}>
            {renderContent()}
          </Grid>
        </Grid>
      </Container>
    </Box>
  );
}

export default UserProfile;
